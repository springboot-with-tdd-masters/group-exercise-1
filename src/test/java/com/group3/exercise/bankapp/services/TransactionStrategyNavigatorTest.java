package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.CheckingAccount;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.exceptions.InvalidAccountTypeException;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import com.group3.exercise.bankapp.services.transaction.impl.CheckingTransactionStrategy;
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

import org.hibernate.mapping.Any;

@ExtendWith(MockitoExtension.class)
public class TransactionStrategyNavigatorTest {

    private TransactionStrategyNavigator navigator;

    @Mock
    private InterestTransactionStrategy interestTxnService;
    
    @Mock
    private CheckingTransactionStrategy checkingTxnStrategy;

    @BeforeEach
    void setup(){
        navigator = new TransactionStrategyNavigatorImpl(interestTxnService, checkingTxnStrategy);
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
    
    @Test
    @DisplayName("should call checking strategy when generateAccountDetails is invoked")
    public void shouldCallCheckingStrategyWhenGenerateAccountDetailsIsInvoked() {
    	//given
    	when(checkingTxnStrategy.generateNewAccountDetails(anyString(), anyString())).thenReturn(new CheckingAccount());
    	
    	//when
    	Account account = navigator.generateNewAccountDetails("Kobe Bryant", "123456789", "checking");
    	
    	//then
    	verify(checkingTxnStrategy, times(1)).generateNewAccountDetails("Kobe Bryant", "123456789");
    	assertEquals("checking", account.getType());
    }
    
    @Test
    @DisplayName("should call checking strategy when withdraw method is called")
    public void shouldCallCheckingStrategyWhenWithdrawMethodIsInvoked() {
    	//given
    	CheckingAccount account = new CheckingAccount();
    	when(checkingTxnStrategy.withdraw(any(CheckingAccount.class), anyDouble())).thenReturn(new CheckingAccount());
    	
    	//when
    	navigator.withdraw(account, 500.0);
    	
    	//then
    	verify(checkingTxnStrategy, times(1)).withdraw(account, 500.0);
    }
    
    @Test
    @DisplayName("should call checking strategy when deposit method is called")
    public void shouldCallCheckingStrategyWhenDepositMethodIsInvoked() {
    	//given
    	CheckingAccount account = new CheckingAccount();
    	when(checkingTxnStrategy.deposit(any(CheckingAccount.class), anyDouble())).thenReturn(new CheckingAccount());
    	
    	//when
    	navigator.deposit(account, 500.0);
    	
    	//then
    	verify(checkingTxnStrategy, times(1)).deposit(account, 500.0);
    }
}
