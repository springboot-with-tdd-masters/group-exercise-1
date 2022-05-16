package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.exceptions.InvalidAccountTypeException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionServiceFactoryImpl implements TransactionServiceFactory {

    private final Map<String, Class<? extends Account>> acctLookup;
    private final Map<Class<? extends Account>, TransactionService> txnLookup;

    public TransactionServiceFactoryImpl(
            InterestTransactionService interestTransactionService
            // TODO add other txn svc here
    ){
        txnLookup = new HashMap<>();
        acctLookup = new HashMap<>();
        acctLookup.put("interest", InterestAccount.class);
        txnLookup.put(InterestAccount.class, interestTransactionService);
    }

    @Override
    public Account generateNewAccountDetails(String name, String acctNumber, String type) {
        Class<? extends  Account> acctType = null;
        if(acctLookup.containsKey(type)){
           acctType = acctLookup.get(type);
        }
        return Optional.ofNullable(acctType)
                    .map(txnLookup::get)
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
