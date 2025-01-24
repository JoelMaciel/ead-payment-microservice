package com.ead.payment.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends EntityNotFoundException {

    public PaymentNotFoundException(String msg) {
        super(msg);
    }
}
