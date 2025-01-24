package com.ead.payment.api.controllers;

import com.ead.payment.api.dtos.PaymentDTO;
import com.ead.payment.api.dtos.PaymentRequestDTO;
import com.ead.payment.domain.models.PaymentModel;
import com.ead.payment.domain.services.PaymentService;
import com.ead.payment.domain.specification.SpecificationTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/users/{userId}/payments")
    public Page<PaymentModel> getAllPayments(
            @PathVariable UUID userId, SpecificationTemplate.PaymentSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "paymentId", direction = Sort.Direction.DESC) Pageable pageable) {
        return paymentService.findAllUser(SpecificationTemplate.paymentUserId(userId).and(spec), pageable);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/users/{userId}/payments/{paymentId}")
    public PaymentDTO getOnePayment(@PathVariable UUID userId, @PathVariable UUID paymentId) {
        return paymentService.findPaymentByUser(userId, paymentId);
    }
}
