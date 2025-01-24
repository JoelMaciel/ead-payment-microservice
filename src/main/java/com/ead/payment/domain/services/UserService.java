package com.ead.payment.domain.services;

import com.ead.payment.api.dtos.UserDTO;
import com.ead.payment.api.dtos.UserEventDTO;
import com.ead.payment.domain.models.UserModel;

import java.util.UUID;

public interface UserService {

    UserDTO saveUser(UserEventDTO userEventDTO);

    void deleteUser(UUID userId);

    UserModel optionalUser(UUID userId);
}
