package com.ead.payment.domain.services;

import com.ead.payment.api.dtos.UserDTO;
import com.ead.payment.api.dtos.UserEventDTO;

import java.util.UUID;

public interface UserService {

    UserDTO saveUser(UserEventDTO userEventDTO);

    void deleteUser(UUID userId);
}
