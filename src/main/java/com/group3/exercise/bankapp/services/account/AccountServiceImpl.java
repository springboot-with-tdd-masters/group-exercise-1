package com.group3.exercise.bankapp.services.account;

import com.group3.exercise.bankapp.adapters.AccountAdapter;
import com.group3.exercise.bankapp.exceptions.AccountTransactionException;
import com.group3.exercise.bankapp.repository.MockAccountRepository;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.request.TransactionResponse;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final TransactionStrategyNavigator transactionStrategyNavigator;
    private final AccountAdapter accountAdapter;
    private final MockAccountRepository accountRepository;
    public AccountServiceImpl(
            TransactionStrategyNavigator transactionStrategyNavigator,
            AccountAdapter accountAdapter,
            MockAccountRepository repository){
        this.accountRepository = repository;
        this.accountAdapter = accountAdapter;
        this.transactionStrategyNavigator = transactionStrategyNavigator;
    }

    @Override
    public TransactionResponse withdraw(Long id, TransactionRequest request) {
        return Optional.ofNullable(id)
                .map(accountRepository::findById)
                .map(found -> this.transactionStrategyNavigator.withdraw(found, request.getAmount()))
                .map(accountRepository::save)
                .map(accountAdapter::mapToResponse)
                .orElseThrow(AccountTransactionException::new);
    }

    @Override
    public TransactionResponse deposit(Long id, TransactionRequest request) {
        return Optional.of(id)
                .map(accountRepository::findById)
                .map(a -> this.transactionStrategyNavigator.deposit(a, request.getAmount()))
                .map(accountRepository::save)
                .map(accountAdapter::mapToResponse)
                .orElseThrow(AccountTransactionException::new);
    }
}
