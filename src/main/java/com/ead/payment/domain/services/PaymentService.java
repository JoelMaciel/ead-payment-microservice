package com.ead.payment.domain.services;

import com.ead.payment.api.dtos.PaymentDTO;
import com.ead.payment.api.dtos.PaymentRequestDTO;
import com.ead.payment.domain.models.UserModel;

import java.util.UUID;

public interface PaymentService {

    PaymentDTO requestPayment(UUID userId, PaymentRequestDTO paymentRequestDTO);

    void validatePaymentStatus(UserModel userModel);
}
