package com.group3.exercise.bankapp.services.impl;

import com.group3.exercise.bankapp.entities.RegularAccount;
import com.group3.exercise.bankapp.exceptions.AccountAlreadyExistsException;
import com.group3.exercise.bankapp.repositories.AccountRepository;
import com.group3.exercise.bankapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegularTransactionService implements TransactionService<RegularAccount> {

    private final Double baseInterestCharge;
    private final Double baseMinimumBalance;
    private final Double basePenalty;

    private final AccountRepository accountRepository;

    public RegularTransactionService(
            AccountRepository accountRepository,
            @Value("${regular.interest_charge}") Double baseInterestCharge,
            @Value("${regular.minimum_balance}") Double baseMinimumBalance,
            @Value("${regular.penalty}") Double basePenalty) {
        this.accountRepository = accountRepository;
        this.baseInterestCharge = baseInterestCharge;
        this.baseMinimumBalance = baseMinimumBalance;
        this.basePenalty = basePenalty;
    }

    @Override
    public RegularAccount generateNewAccountDetails(String name, String acctNumber) {

        accountRepository.findAccountByAcctNumber(acctNumber)
                .ifPresent(account -> {
                    throw new AccountAlreadyExistsException();
                });

        final RegularAccount regularAccount = new RegularAccount(name, acctNumber);

        regularAccount.setInterestCharge(baseInterestCharge);
        regularAccount.setMinimumBalance(baseMinimumBalance);
        regularAccount.setPenalty(basePenalty);
        regularAccount.setBalance(baseMinimumBalance);

        return accountRepository.save(regularAccount);
    }

    @Override
    public RegularAccount withdraw(RegularAccount account, Double amount) {

        final Double currentBalance = account.getBalance();

        double newBalance = currentBalance - amount;

        if (newBalance < account.getMinimumBalance()) {
            newBalance = newBalance - account.getPenalty();
        }

        account.setBalance(newBalance);

        return accountRepository.save(account);
    }

    @Override
    public RegularAccount deposit(RegularAccount account, Double amount) {
        return null;
    }

}
