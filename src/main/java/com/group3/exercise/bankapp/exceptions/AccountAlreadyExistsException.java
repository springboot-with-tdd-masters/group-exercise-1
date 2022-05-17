package com.group3.exercise.bankapp.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException() {
        super("Account already exists");
    }
}
