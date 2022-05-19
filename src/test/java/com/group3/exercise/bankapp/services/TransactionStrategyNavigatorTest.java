package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.exceptions.InvalidAccountTypeException;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import com.group3.exercise.bankapp.services.transaction.impl.InterestTransactionStrategy;
import com.group3.exercise.bankapp.services.transaction.impl.TransactionStrategyNavigatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionStrategyNavigatorTest {

    private TransactionStrategyNavigator navigator;

    @Mock
    private InterestTransactionStrategy interestTxnService;

    @BeforeEach
    void setup(){
        navigator = new TransactionStrategyNavigatorImpl(interestTxnService);
    }

    @Test
    @DisplayName("should call interest service when generate accountDetails")
    void shouldCallInterestTransactionServiceWhenGenerateAccountDetails(){
        // given
        when(interestTxnService.generateNewAccountDetails(anyString(), anyString())).thenReturn(new InterestAccount());
        // when
        Account account = navigator.generateNewAccountDetails("JOHN DOE", "987654321", "interest");
        // then
        verify(interestTxnService, times(1)).generateNewAccountDetails("JOHN DOE", "987654321");
        assertEquals("interest",account.getType());
    }

    @Test
    @DisplayName("should throw InvalidAccountTypeException")
    void shouldThrowExceptionWhenInvalidAccountType(){
        // given
        // when
        // then
        assertThrows(InvalidAccountTypeException.class, () -> navigator.generateNewAccountDetails("JOHN DOE", "987654321", "credit"));
    }

    @Test
    @DisplayName("should call interest service when withdraw is called")
    void shouldCallInterestTransactionStrategyWhenWithdrawIsCalled(){
        // given
        Account stub = new InterestAccount();
        when(interestTxnService.withdraw(any(Account.class), anyDouble())).thenReturn(new InterestAccount());
        // when
        navigator.withdraw(stub, 200.0);
        // then
        verify(interestTxnService, times(1)).withdraw(stub, 200.0);
    }
    @Test
    @DisplayName("should call interest service when deposit is called")
    void shouldCallInterestTransactionStrategyWhenDepositIsCalled(){
        // given
        Account stub = new InterestAccount();
        when(interestTxnService.deposit(any(Account.class), anyDouble())).thenReturn(new InterestAccount());
        // when
        navigator.deposit(stub, 200.0);
        // then
        verify(interestTxnService, times(1)).deposit(stub, 200.0);
    }
}
