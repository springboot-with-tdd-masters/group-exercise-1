package com.group3.exercise.bankapp.exceptions;

public class AccountTransactionException extends RuntimeException {

    private final String errorMsg;
    public AccountTransactionException(String errorMsg){
        super();
        this.errorMsg = errorMsg;
    }
    public AccountTransactionException(){
        super();
        this.errorMsg = "Unable to process transaction";
    }
    public String getErrorMsg() {
        return errorMsg;
    }
}
