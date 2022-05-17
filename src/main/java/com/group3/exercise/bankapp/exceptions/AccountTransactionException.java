package com.group3.exercise.bankapp.exceptions;

import org.springframework.http.HttpStatus;

public class AccountTransactionException extends RuntimeException {

    private final String errorMsg;
    private final HttpStatus httpStatus;

    public AccountTransactionException(String errorMsg){
        super();
        this.errorMsg = errorMsg;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public AccountTransactionException(){
        super();
        this.errorMsg = "Unable to process transaction";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
}
