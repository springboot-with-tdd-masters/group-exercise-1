package com.group3.exercise.bankapp.services.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;

public class InterestTransactionService implements TransactionService {

    private Double interest;
    private Double minimumBalance;

    public InterestTransactionService(
            @Value("${interest.charge:.03}") Double interest,
            @Value("${interest.minimumBalance}") Double minimumBalance){
        this.interest = interest;
        this.minimumBalance = minimumBalance;
    }


    @Override
    public Account generateNewAccountDetails(String name, String acctNumber, String type) {
        // TODO generate Account entity here.
        Account account = new InterestAccount();
        account.setInterestCharge(interest);
        account.setMinimumBalance(minimumBalance);
        return account;
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
