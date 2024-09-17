package com.ead.payment.domain.services.impl;

import com.ead.payment.domain.repositories.PaymentRepository;
import com.ead.payment.domain.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
}
