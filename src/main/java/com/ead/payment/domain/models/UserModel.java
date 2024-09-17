package com.ead.payment.domain.models;

import com.ead.payment.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "USERS")
public class UserModel {

    @Id
    private UUID userId;
    private String email;
    private String username;
    private String fullName;
    private String userStatus;
    private String userType;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private OffsetDateTime paymentExpirationDate;
    private OffsetDateTime firstPaymentDate;
    private OffsetDateTime lastPaymentDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<PaymentModel> payments;
}
