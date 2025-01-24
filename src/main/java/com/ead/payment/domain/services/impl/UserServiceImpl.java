package com.ead.payment.domain.services.impl;

import com.ead.payment.api.dtos.UserDTO;
import com.ead.payment.api.dtos.UserEventDTO;
import com.ead.payment.api.converter.UserConverter;
import com.ead.payment.domain.enums.PaymentStatus;
import com.ead.payment.domain.exceptions.UserNotFoundException;
import com.ead.payment.domain.models.UserModel;
import com.ead.payment.domain.repositories.UserRepository;
import com.ead.payment.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    @Transactional
    @Override
    public UserDTO saveUser(UserEventDTO userEventDTO) {
        UserModel userModel = userConverter.toEntity(userEventDTO);
        userModel.setPaymentStatus(PaymentStatus.NOTSTARTED);
        return userConverter.toDTO(userRepository.save(userModel));
    }

    @Transactional
    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserModel optionalUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
