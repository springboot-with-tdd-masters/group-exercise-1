package com.softvision.bank.tdd.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientFundsAvailable extends RuntimeException {
    public InsufficientFundsAvailable() {
        super("Sorry, You have insufficient funds available.");
    }
}
