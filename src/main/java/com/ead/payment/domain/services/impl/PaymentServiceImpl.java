package com.ead.payment.domain.services.impl;

import com.ead.payment.api.converter.PaymentConverter;
import com.ead.payment.api.dtos.PaymentDTO;
import com.ead.payment.api.dtos.PaymentRequestDTO;
import com.ead.payment.domain.enums.PaymentControl;
import com.ead.payment.domain.exceptions.PaymentException;
import com.ead.payment.domain.exceptions.PaymentNotFoundException;
import com.ead.payment.domain.models.CreditCardModel;
import com.ead.payment.domain.models.PaymentModel;
import com.ead.payment.domain.models.UserModel;
import com.ead.payment.domain.repositories.CreditCardRepository;
import com.ead.payment.domain.repositories.PaymentRepository;
import com.ead.payment.domain.services.PaymentService;
import com.ead.payment.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final CreditCardRepository creditCardRepository;
    private final PaymentConverter paymentConverter;


    @Transactional
    @Override
    public PaymentDTO requestPayment(UUID userId, PaymentRequestDTO paymentRequestDTO) {
        UserModel userModel = userService.optionalUser(userId);
        validatePaymentStatus(userModel);

        var optionalCreditCard = creditCardRepository.findByUser(userModel);

        CreditCardModel creditCardModel = paymentConverter.converterToCreditCard(paymentRequestDTO);

        if (optionalCreditCard.isPresent()) {
            creditCardModel = optionalCreditCard.get();
        }

        creditCardModel.setUser(userModel);
        creditCardRepository.save(creditCardModel);

        PaymentModel paymentModel = paymentConverter.toEntity(paymentRequestDTO);
        paymentModel.setUser(userModel);
        paymentRepository.save(paymentModel);
        return paymentConverter.toDTO(paymentModel, userId);
    }

    @Override
    public Page<PaymentModel> findAllUser(Specification<PaymentModel> spec, Pageable pageable) {
        return paymentRepository.findAll(spec, pageable);
    }

    @Override
    public PaymentDTO findPaymentByUser(UUID userId, UUID paymentId) {
        PaymentModel paymentByUser = optionalPaymentByUser(userId, paymentId);
        return paymentConverter.toDTO(paymentByUser, userId);
    }

    private PaymentModel optionalPaymentByUser(UUID userId, UUID paymentId) {
        return paymentRepository.findPaymentByUser(userId, paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for this user"));
    }

    @Override
    public void validatePaymentStatus(UserModel userModel) {
        Optional<PaymentModel> lastPayment = paymentRepository.findTopByUserOrderByPaymentRequestDateDesc(userModel);

        lastPayment.ifPresent(payment -> {
            validateIfRequested(payment);
            validateIfEffectedAndValid(payment);
        });
    }

    private void validateIfRequested(PaymentModel payment) {
        if (payment.getPaymentControl().equals(PaymentControl.REQUESTED)) {
            throw new PaymentException("Payment already requested");
        }
    }

    private void validateIfEffectedAndValid(PaymentModel payment) {
        if (payment.getPaymentControl().equals(PaymentControl.EFFECTED) &&
                payment.getPaymentExpirationDate().isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            throw new PaymentException("Payment already made and still valid");
        }
    }

}
