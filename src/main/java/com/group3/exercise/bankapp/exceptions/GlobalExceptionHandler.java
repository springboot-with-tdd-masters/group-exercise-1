package com.group3.exercise.bankapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountTransactionException.class)
    public ResponseEntity<ErrorMsgWrapper> handleInvalidTransactionException(AccountTransactionException ex, WebRequest webRequest){
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorMsgWrapper(ex.getErrorMsg()));
    }

    @ExceptionHandler(InvalidAccountTypeException.class)
    public ResponseEntity<ErrorMsgWrapper> handleInvalidAccountTypeException(InvalidAccountTypeException ex, WebRequest webRequest){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMsgWrapper("Invalid Account Type"));
    }
}
