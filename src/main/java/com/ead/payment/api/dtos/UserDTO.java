package com.ead.payment.api.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private UUID userId;
    private String email;
    private String username;
    private String fullName;
    private String userStatus;
    private String userType;
    private String cpf;
    private String actionType;
}
