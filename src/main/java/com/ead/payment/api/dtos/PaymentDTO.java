package com.ead.payment.api.dtos;

import com.ead.payment.domain.enums.PaymentControl;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {

    private UUID paymentId;
    private PaymentControl paymentControl;
    private UUID userId;
    private OffsetDateTime paymentRequestDate;
    private OffsetDateTime paymentCompletionDate;
    private OffsetDateTime paymentExpirationDate;
    private String lastDigitsCreditCard;
    private BigDecimal valuePaid;
    private String paymentMessage;
    private boolean recurrence;
}
