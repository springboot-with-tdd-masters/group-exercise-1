package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.entities.RegularAccount;
import com.group3.exercise.bankapp.exceptions.InvalidAccountTypeException;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import com.group3.exercise.bankapp.services.transaction.impl.InterestTransactionStrategy;
import com.group3.exercise.bankapp.services.transaction.impl.RegularTransactionStrategy;
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

    @Mock
    private RegularTransactionStrategy regularTxnStrategy;

    @BeforeEach
    void setup(){
        navigator = new TransactionStrategyNavigatorImpl(
                interestTxnService,
                regularTxnStrategy
        );
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
        InterestAccount stub = new InterestAccount();
        when(interestTxnService.withdraw(any(InterestAccount.class), anyDouble())).thenReturn(new InterestAccount());
        // when
        navigator.withdraw(stub, 200.0);
        // then
        verify(interestTxnService, times(1)).withdraw(stub, 200.0);
    }
    @Test
    @DisplayName("should call interest service when deposit is called")
    void shouldCallInterestTransactionStrategyWhenDepositIsCalled(){
        // given
        InterestAccount stub = new InterestAccount();
        when(interestTxnService.deposit(any(InterestAccount.class), anyDouble())).thenReturn(new InterestAccount());
        // when
        navigator.deposit(stub, 200.0);
        // then
        verify(interestTxnService, times(1)).deposit(stub, 200.0);
    }

    // Regular Transaction

    @Test
    @DisplayName("should call regular service when generate accountDetails")
    void shouldCallRegularTransactionServiceWhenGenerateAccountDetails(){
        // given
        when(regularTxnStrategy.generateNewAccountDetails(anyString(), anyString())).thenReturn(new RegularAccount());
        // when
        Account account = navigator.generateNewAccountDetails("James", "987654321", "regular");
        // then
        verify(regularTxnStrategy, times(1)).generateNewAccountDetails("James", "987654321");
        assertEquals("regular", account.getType());
    }

    @Test
    @DisplayName("should call regular service when withdraw is called")
    void shouldCallRegularTransactionStrategyWhenWithdrawIsCalled(){
        // given
        RegularAccount stub = new RegularAccount();
        when(regularTxnStrategy.withdraw(any(RegularAccount.class), anyDouble())).thenReturn(new RegularAccount());
        // when
        navigator.withdraw(stub, 200.0);
        // then
        verify(regularTxnStrategy, times(1)).withdraw(stub, 200.0);
    }
    @Test
    @DisplayName("should call regular service when deposit is called")
    void shouldCallRegularTransactionStrategyWhenDepositIsCalled(){
        // given
        RegularAccount stub = new RegularAccount();
        when(regularTxnStrategy.deposit(any(RegularAccount.class), anyDouble())).thenReturn(new RegularAccount());
        // when
        navigator.deposit(stub, 200.0);
        // then
        verify(regularTxnStrategy, times(1)).deposit(stub, 200.0);
    }

}
