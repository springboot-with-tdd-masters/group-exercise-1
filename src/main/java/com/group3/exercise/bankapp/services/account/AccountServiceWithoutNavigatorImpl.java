package com.group3.exercise.bankapp.services.account;

import com.group3.exercise.bankapp.adapters.AccountAdapter;
import com.group3.exercise.bankapp.repository.MockAccountRepository;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.request.TransactionResponse;

public class AccountServiceWithoutNavigatorImpl implements AccountService{

    public AccountServiceWithoutNavigatorImpl(
            AccountAdapter adapter,
            MockAccountRepository repository){

    }


    @Override
    public TransactionResponse withdraw(Long id, TransactionRequest request) {


        return null;
    }

    @Override
    public TransactionResponse deposit(Long id, TransactionRequest request) {
        return null;
    }
}
