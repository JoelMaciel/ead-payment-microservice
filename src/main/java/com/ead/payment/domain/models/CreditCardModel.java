package com.ead.payment.domain.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCardModel {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cardId;

    private String cardHolderFullName;
    private String cardHolderCpf;
    private String creditCardNumber;
    private String expirationDate;
    private String cvvCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
