package com.ead.payment.api.controllers;

import com.ead.payment.api.dtos.PaymentDTO;
import com.ead.payment.api.dtos.PaymentRequestDTO;
import com.ead.payment.domain.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/users/{userId}")
    public PaymentDTO requestPayment(@PathVariable UUID userId, @RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        return paymentService.requestPayment(userId, paymentRequestDTO);
    }
}
