package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.entities.Account;

public interface TransactionService<E extends Account> {
    E generateNewAccountDetails(String name, String acctNumber);
    E withdraw(E account, Double amount);
    E deposit(E account, Double amount);
}
