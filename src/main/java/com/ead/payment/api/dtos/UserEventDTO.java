package com.ead.payment.api.dtos;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEventDTO {

    private UUID userId;
    private String username;
    private String fullName;
    private String email;
    private String userStatus;
    private String userType;
    private String cpf;
    private String actionType;
}