package com.group3.exercise.bankapp.services.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.RegularAccount;
import com.group3.exercise.bankapp.exceptions.AccountAlreadyExistsException;
import com.group3.exercise.bankapp.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RegularTransactionServiceTest {

    private final static Double BASE_INTEREST_CHARGE = 0d;
    private final static Double BASE_MINIMUM_BALANCE = 500d;
    private final static Double BASE_PENALTY = 10d;

    private RegularTransactionService regularTransactionService;

    private AccountRepository accountRepository = mock(AccountRepository.class);

    @BeforeEach
    void setUp() {
        regularTransactionService = new RegularTransactionService(
                accountRepository,
                BASE_INTEREST_CHARGE,
                BASE_MINIMUM_BALANCE,
                BASE_PENALTY
        );
    }

    @Test
    @DisplayName("shouldGenerateNewAccountDetails")
    void shouldGenerateNewAccountDetails() {
        // Arrange
        String mockName = "James";
        String mockAccountNumber = "321456";

        final RegularAccount savedRegularAccountFromDatabase = new RegularAccount(mockName, mockAccountNumber);
        savedRegularAccountFromDatabase.setInterestCharge(BASE_INTEREST_CHARGE);
        savedRegularAccountFromDatabase.setMinimumBalance(BASE_MINIMUM_BALANCE);
        savedRegularAccountFromDatabase.setPenalty(BASE_PENALTY);
        savedRegularAccountFromDatabase.setBalance(BASE_MINIMUM_BALANCE);
        when(accountRepository.save(savedRegularAccountFromDatabase))
                .thenReturn(savedRegularAccountFromDatabase);

        // Act
        final RegularAccount newlyGeneratedAccount = regularTransactionService.generateNewAccountDetails(mockName, mockAccountNumber);

        // Assert
        verify(accountRepository)
                .save(savedRegularAccountFromDatabase);

        assertThat(newlyGeneratedAccount)
                .isNotNull()
                .extracting("name", "acctNumber")
                .contains(mockName, mockAccountNumber);

        assertThat(newlyGeneratedAccount)
                .isInstanceOf(RegularAccount.class);

        // Business rules
        assertThat(newlyGeneratedAccount)
                .extracting("interestCharge", "minimumBalance", "penalty", "balance")
                .containsExactly(BASE_INTEREST_CHARGE, BASE_MINIMUM_BALANCE, BASE_PENALTY, BASE_MINIMUM_BALANCE);

    }

    @Test
    @DisplayName("shouldThrowAccountAlreadyExistsIfAccountNumberAlreadyInDatabase")
    void shouldThrowAccountAlreadyExistsIfAccountNumberAlreadyInDatabase() {
        // Arrange
        String mockName = "James";
        String mockExistingAccountNumber = "112244";

        Account mockedExistingAccount = new RegularAccount(mockName, mockExistingAccountNumber);
        when(accountRepository.findAccountByAcctNumber(mockExistingAccountNumber))
                .thenReturn(Optional.ofNullable(mockedExistingAccount));

        // Act
        org.junit.jupiter.api.Assertions.assertThrows(
                AccountAlreadyExistsException.class,
                () -> regularTransactionService.generateNewAccountDetails(mockName, mockExistingAccountNumber),
                "Account already exists"
        );

        // Assert
        verify(accountRepository)
                .findAccountByAcctNumber(mockExistingAccountNumber);
    }

    @Test
    @DisplayName("shouldDeductAmountToBalanceUponWithdraw")
    void shouldDeductAmountToBalanceUponWithdraw() {
        // Arrange
        String mockAccountNumber = "1234";
        String mockName = "James";
        Double mockWithdrawalAmount = 100d;

        final RegularAccount mockedAccount = new RegularAccount(mockName, mockAccountNumber);
        mockedAccount.setPenalty(BASE_PENALTY);
        mockedAccount.setMinimumBalance(BASE_MINIMUM_BALANCE);
        mockedAccount.setBalance(600d);

        when(accountRepository.save(mockedAccount))
                .thenReturn(mockedAccount);

        // Act
        final RegularAccount regularAccount = regularTransactionService.withdraw(mockedAccount, mockWithdrawalAmount);

        // Assert
        verify(accountRepository)
                .save(mockedAccount);

        assertThat(regularAccount)
                .isNotNull()
                .extracting("balance", "penalty", "minimumBalance")
                .containsExactly(500d, BASE_PENALTY, BASE_MINIMUM_BALANCE);
    }

    @Test
    @DisplayName("shouldApplyPenaltyToBalanceWhenBelowMinimumBalance")
    void shouldApplyPenaltyToBalanceWhenBelowMinimumBalance() {
        // Arrange
        String mockAccountNumber = "4321";
        String mockName = "James";
        Double mockWithdrawalAmount = 300d;

        final RegularAccount mockedAccount = new RegularAccount(mockName, mockAccountNumber);
        mockedAccount.setPenalty(BASE_PENALTY);
        mockedAccount.setMinimumBalance(BASE_MINIMUM_BALANCE);
        mockedAccount.setBalance(500d);

        when(accountRepository.save(mockedAccount))
                .thenReturn(mockedAccount);

        // Act
        final RegularAccount regularAccount = regularTransactionService.withdraw(mockedAccount, mockWithdrawalAmount);

        // Assert
        verify(accountRepository)
                .save(mockedAccount);

        assertThat(regularAccount)
                .isNotNull()
                .extracting("balance", "penalty", "minimumBalance")
                .containsExactly(190d, BASE_PENALTY, BASE_MINIMUM_BALANCE);
    }

}
