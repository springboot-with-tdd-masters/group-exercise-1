package com.group3.exercise.bankapp.services.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;

public class InterestTransactionService implements TransactionService {

    private final Double interest;
    private final Double minimumBalance;

    public InterestTransactionService(
            @Value("${interest.charge:.03}") Double interest,
            @Value("${interest.minimumBalance}") Double minimumBalance){
        this.interest = interest;
        this.minimumBalance = minimumBalance;
    }


    @Override
    public Account generateNewAccountDetails(String name, String acctNumber) {
        // TODO generate Account entity here.
        Account account = new InterestAccount();
        account.setInterestCharge(interest);
        account.setMinimumBalance(minimumBalance);
        return account;
    }

    @Override
    public Account withdraw(Account account, Double amount) {
        // TODO update account entity with business rules
        Double currentBalance = account.getBalance();
        Double updated = currentBalance - amount;
        account.setBalance(updated);
        return account;
    }

    @Override
    public Account deposit(Account account, Double amount) {
        // TODO update account entity with business rules
        Double currentBalance = account.getBalance();
        Double updated = currentBalance + amount;
        account.setBalance(updated);
        return account;
    }
}
