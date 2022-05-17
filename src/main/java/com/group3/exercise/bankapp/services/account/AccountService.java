package com.group3.exercise.bankapp.services.account;

import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.request.TransactionResponse;

public interface AccountService {
    TransactionResponse withdraw(Long id, TransactionRequest request);
    TransactionResponse deposit(Long id, TransactionRequest request);
}
