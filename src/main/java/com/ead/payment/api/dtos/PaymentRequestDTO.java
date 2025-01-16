package com.ead.payment.api.dtos;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    @NotNull
    @PositiveOrZero
    private BigDecimal valuePaid;

    @NotBlank
    private String cardHolderFullName;

    @NotBlank
    @CPF
    private String cardHolderCpf;

    @NotBlank
    @Size(min = 16, max = 20)
    private String creditCardNumber;

    @NotBlank
    @Size(min = 4, max = 10)
    private String expirationDate;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cvvCode;

}
