package com.group3.exercise.bankapp.services.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.services.TransactionService;

public class InterestTransactionService implements TransactionService {
    @Override
    public Account generateNewAccountDetails(String name, String acctNumber, String type) {
        // TODO generate Account entity here.
        return null;
    }

    @Override
    public Account withdraw(Account account, Double amount) {
        // TODO update account entity with business rules
        return null;
    }

    @Override
    public Account deposit(Account account, Double amount) {
        // TODO update account entity with business rules
        return null;
    }
}
