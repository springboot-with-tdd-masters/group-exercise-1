package com.group3.exercise.bankapp.exceptions;

import org.springframework.http.HttpStatus;

public enum BankAppExceptionCode {
<<<<<<< HEAD
    // Transaction Exceptions
    TRANSACTION_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "Invalid Transaction Type"),
    INVALID_AMOUNT_EXCEPTION(HttpStatus.BAD_REQUEST, "Please insert a valid amount"),
    ACCOUNT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "Unable to process your request. Account does not exists"),
    // TODO replace this with your specific exception code.
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "your bad request message"),
    MAPPING_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to map response"),
    SERVER_TRANSACTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process your request"),
=======
    TRANSACTION_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "Invalid Transaction Type"),
    // TODO replace this with your specific exception code.
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "your bad request message"),
>>>>>>> group3/exceptions
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "your internal server error message")
    ;


    private HttpStatus status;
    private String message;


    private BankAppExceptionCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
