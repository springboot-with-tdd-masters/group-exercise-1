package com.group3.exercise.bankapp.services.transaction.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.entities.RegularAccount;
import com.group3.exercise.bankapp.exceptions.InvalidAccountTypeException;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategy;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionStrategyNavigatorImpl implements TransactionStrategyNavigator {

    private final Map<Class<? extends Account>, TransactionStrategy> txnLookup;
    private final Map<String, TransactionStrategy> txnTypeLookup;

    public TransactionStrategyNavigatorImpl(
            InterestTransactionStrategy interestTransactionStrategy,
            RegularTransactionStrategy regularTransactionStrategy
            // TODO add other txn svc here
    ){
        txnLookup = new HashMap<>();
        txnTypeLookup = new HashMap<>();
        txnLookup.put(InterestAccount.class, interestTransactionStrategy);
        txnLookup.put(RegularAccount.class, regularTransactionStrategy);
        txnTypeLookup.put(InterestAccount.TYPE, interestTransactionStrategy);
        txnTypeLookup.put(RegularAccount.TYPE, regularTransactionStrategy);
    }

    @Override
    public Account generateNewAccountDetails(String name, String acctNumber, String type) {
        return Optional.ofNullable(txnTypeLookup.get(type))
                .map(txn -> txn.generateNewAccountDetails(name, acctNumber))
                .orElseThrow(InvalidAccountTypeException::new);
    }

    @Override
    public Account withdraw(Account account, Double amount) {
       return Optional.ofNullable(txnLookup.get(account.getClass()))
                .map(txn -> txn.withdraw(account, amount))
                .orElseThrow(InvalidAccountTypeException::new);
    }

    @Override
    public Account deposit(Account account, Double amount) {
        return Optional.ofNullable(txnLookup.get(account.getClass()))
                .map(txn -> txn.deposit(account, amount))
                .orElseThrow(InvalidAccountTypeException::new);
    }
}
