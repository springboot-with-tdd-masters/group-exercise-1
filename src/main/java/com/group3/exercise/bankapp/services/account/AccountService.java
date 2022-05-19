package com.group3.exercise.bankapp.services.account;

import com.group3.exercise.bankapp.request.CreateAccountRequest;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.response.AccountResponse;

public interface AccountService {
    AccountResponse register(CreateAccountRequest request);
    AccountResponse withdraw(Long id, TransactionRequest request);
    AccountResponse deposit(Long id, TransactionRequest request);
}
