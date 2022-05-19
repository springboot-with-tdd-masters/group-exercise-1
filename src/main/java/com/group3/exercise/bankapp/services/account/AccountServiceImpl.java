package com.group3.exercise.bankapp.services.account;

import com.group3.exercise.bankapp.adapters.AccountAdapter;
import com.group3.exercise.bankapp.exceptions.AccountTransactionException;
import com.group3.exercise.bankapp.repository.AccountRepository;
import com.group3.exercise.bankapp.request.CreateAccountRequest;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.response.AccountResponse;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    private final TransactionStrategyNavigator transactionStrategyNavigator;
    private final AccountAdapter accountAdapter;
    private final AccountRepository accountRepository;
    public AccountServiceImpl(
            TransactionStrategyNavigator transactionStrategyNavigator,
            AccountAdapter accountAdapter,
            AccountRepository repository){
        this.accountRepository = repository;
        this.accountAdapter = accountAdapter;
        this.transactionStrategyNavigator = transactionStrategyNavigator;
    }

    @Override
    public AccountResponse register(CreateAccountRequest request) {
        return Optional.ofNullable(request)
                .map(req -> this.transactionStrategyNavigator.generateNewAccountDetails(req.getName(),generateAcctNbr() ,req.getType()))
                .map(this.accountRepository::save)
                .map(accountAdapter::mapToResponse)
                .orElseThrow(AccountTransactionException::new);
    }

    @Override
    public AccountResponse withdraw(Long id, TransactionRequest request) {
        return Optional.ofNullable(id)
                .map(accountRepository::findById)
                .map(Optional::get)
                .map(found -> this.transactionStrategyNavigator.withdraw(found, request.getAmount()))
                .map(accountRepository::save)
                .map(accountAdapter::mapToResponse)
                .orElseThrow(AccountTransactionException::new);
    }

    @Override
    public AccountResponse deposit(Long id, TransactionRequest request) {
        return Optional.of(id)
                .map(accountRepository::findById)
                .map(Optional::get)
                .map(a -> this.transactionStrategyNavigator.deposit(a, request.getAmount()))
                .map(accountRepository::save)
                .map(accountAdapter::mapToResponse)
                .orElseThrow(AccountTransactionException::new);
    }
    private String generateAcctNbr(){
        return String.valueOf(new Random().nextInt(99999999));
    }
}
