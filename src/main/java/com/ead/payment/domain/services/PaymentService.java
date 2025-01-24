package com.ead.payment.domain.services;

import com.ead.payment.api.dtos.PaymentDTO;
import com.ead.payment.api.dtos.PaymentRequestDTO;
import com.ead.payment.domain.models.PaymentModel;
import com.ead.payment.domain.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface PaymentService {

    PaymentDTO requestPayment(UUID userId, PaymentRequestDTO paymentRequestDTO);

    void validatePaymentStatus(UserModel userModel);

    Page<PaymentModel> findAllUser(Specification<PaymentModel> spec, Pageable pageable);

    PaymentDTO findPaymentByUser(UUID userId, UUID paymentId);
}
