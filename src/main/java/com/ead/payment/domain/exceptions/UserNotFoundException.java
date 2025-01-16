package com.ead.payment.domain.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(UUID userId) {
        super(String.format("User %s not found in database", userId));
    }
}
