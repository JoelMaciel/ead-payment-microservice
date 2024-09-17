package com.ead.payment.api.dtos.converter;

import com.ead.payment.api.dtos.UserDTO;
import com.ead.payment.api.dtos.UserEventDTO;
import com.ead.payment.domain.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private UserConverter() {
    }

    public UserModel toEntity(UserEventDTO userEventDTO) {
        return UserModel.builder()
                .userId(userEventDTO.getUserId())
                .username(userEventDTO.getUsername())
                .email(userEventDTO.getEmail())
                .fullName(userEventDTO.getFullName())
                .cpf(userEventDTO.getCpf())
                .userStatus(userEventDTO.getUserStatus())
                .userType(userEventDTO.getUserType())
                .build();
    }

    public UserDTO toDTO(UserModel user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .cpf(user.getCpf())
                .userStatus(user.getUserStatus())
                .userType(user.getUserType())
                .build();
    }

}
