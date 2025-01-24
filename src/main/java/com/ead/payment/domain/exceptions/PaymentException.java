package com.ead.payment.domain.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PaymentException extends DataIntegrityViolationException {

    public PaymentException(String msg) {
        super(msg);
    }
}
