package com.group3.exercise.bankapp.controllers;

import com.group3.exercise.bankapp.constants.TransactionTypes;
import com.group3.exercise.bankapp.exceptions.BankAppException;
import com.group3.exercise.bankapp.exceptions.BankAppExceptionCode;
import com.group3.exercise.bankapp.request.CreateAccountRequest;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.response.AccountResponse;
import com.group3.exercise.bankapp.services.account.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public AccountResponse registerAccount(@RequestBody CreateAccountRequest request){
        return service.register(request);
    }

    @PostMapping(path = "/{accountId}/transactions")
    public AccountResponse transact(@PathVariable("accountId") Long accountId, @RequestBody TransactionRequest request){
        if(TransactionTypes.isContaining(request.getType())){
            if(TransactionTypes.DEPOSIT.value().equals(request.getType())){
               return this.service.deposit(accountId, request);
            } else if(TransactionTypes.WITHDRAW.value().equals(request.getType())){
               return this.service.withdraw(accountId, request);
            }
        }
        throw new BankAppException(BankAppExceptionCode.TRANSACTION_TYPE_EXCEPTION);
    }
}
