package com.group3.exercise.bankapp.services.transaction;

import com.group3.exercise.bankapp.entities.Account;

public interface TransactionStrategy {
    Account generateNewAccountDetails(String name, String acctNumber);
    Account withdraw(Account account, Double amount);
    Account deposit(Account account, Double amount);
}
