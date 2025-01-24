package com.ead.payment.api.converter;

import com.ead.payment.api.dtos.PaymentDTO;
import com.ead.payment.api.dtos.PaymentRequestDTO;
import com.ead.payment.domain.enums.PaymentControl;
import com.ead.payment.domain.models.CreditCardModel;
import com.ead.payment.domain.models.PaymentModel;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class PaymentConverter {

    private PaymentConverter(){

    }

    public CreditCardModel converterToCreditCard(PaymentRequestDTO paymentRequestDTO) {
        return CreditCardModel.builder()
                .creditCardNumber(paymentRequestDTO.getCreditCardNumber())
                .cardHolderCpf(paymentRequestDTO.getCardHolderCpf())
                .cardHolderFullName(paymentRequestDTO.getCardHolderFullName())
                .expirationDate(paymentRequestDTO.getExpirationDate())
                .cvvCode(paymentRequestDTO.getCvvCode())
                .build();
    }

    public PaymentModel toEntity(PaymentRequestDTO paymentRequestDTO) {
        return PaymentModel.builder()
                .paymentControl(PaymentControl.REQUESTED)
                .paymentRequestDate(OffsetDateTime.now())
                .paymentExpirationDate(OffsetDateTime.now().plusDays(30))
                .lastDigitsCreditCard(paymentRequestDTO.getCreditCardNumber().substring(paymentRequestDTO.getCreditCardNumber().length() - 4))
                .valuePaid(paymentRequestDTO.getValuePaid())
                .build();
    }

    public PaymentDTO toDTO(PaymentModel paymentModel, UUID userId) {
        return PaymentDTO.builder()
                .paymentId(paymentModel.getPaymentId())
                .paymentControl(paymentModel.getPaymentControl())
                .paymentRequestDate(paymentModel.getPaymentRequestDate())
                .paymentCompletionDate(paymentModel.getPaymentCompletionDate())
                .paymentExpirationDate(paymentModel.getPaymentExpirationDate())
                .lastDigitsCreditCard(paymentModel.getLastDigitsCreditCard())
                .valuePaid(paymentModel.getValuePaid())
                .paymentMessage(paymentModel.getPaymentMessage())
                .recurrence(false)
                .userId(userId)
                .build();
    }
}
