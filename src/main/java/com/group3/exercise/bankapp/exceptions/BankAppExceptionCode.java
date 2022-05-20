package com.group3.exercise.bankapp.exceptions;

import org.springframework.http.HttpStatus;

public enum BankAppExceptionCode {
    TRANSACTION_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "Invalid Transaction Type"),
    // TODO replace this with your specific exception code.
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "your bad request message"),
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
