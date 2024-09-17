package com.ead.payment.domain.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
