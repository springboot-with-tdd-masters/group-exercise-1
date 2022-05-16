package com.group3.exercise.bankapp.services.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.exceptions.InvalidAccountTypeException;
import com.group3.exercise.bankapp.services.TransactionServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceFactoryTest {

    private TransactionServiceFactory factory;

    @Mock
    private InterestTransactionService interestTxnService;

    @BeforeEach
    void setup(){
        factory = new TransactionServiceFactoryImpl(interestTxnService);
    }

    @Test
    @DisplayName("should call interest service when generate accountDetails")
    void shouldCallInterestTransactionServiceWhenGenerateAccountDetails(){
        // given
        when(interestTxnService.generateNewAccountDetails(anyString(), anyString())).thenReturn(new InterestAccount());
        // when
        factory.generateNewAccountDetails("JOHN DOE", "987654321", "interest");
        // then
        verify(interestTxnService, times(1)).generateNewAccountDetails("JOHN DOE", "987654321");
    }

    @Test
    @DisplayName("should throw InvalidAccountTypeException")
    void shouldThrowExceptionWhenInvalidAccountType(){
        // given
        // when
        // then
        assertThrows(InvalidAccountTypeException.class, () -> factory.generateNewAccountDetails("JOHN DOE", "987654321", "credit"));
    }

    @Test
    @DisplayName("should call interest service when withdraw is called")
    void shouldCallInterestTransactionServiceWhenWithdrawIsCalled(){
        // given
        Account stub = new InterestAccount();
        when(interestTxnService.withdraw(any(Account.class), anyDouble())).thenReturn(new InterestAccount());
        // when
        factory.withdraw(stub, 200.0);
        // then
        verify(interestTxnService, times(1)).withdraw(stub, 200.0);
    }
    @Test
    @DisplayName("should call interest service when deposit is called")
    void shouldCallInterestTransactionServiceWhenDepositIsCalled(){
        // given
        Account stub = new InterestAccount();
        when(interestTxnService.deposit(any(Account.class), anyDouble())).thenReturn(new InterestAccount());
        // when
        factory.deposit(stub, 200.0);
        // then
        verify(interestTxnService, times(1)).deposit(stub, 200.0);
    }
}
