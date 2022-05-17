package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.adapters.AccountAdapter;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.exceptions.AccountTransactionException;
import com.group3.exercise.bankapp.repository.MockAccountRepository;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.request.TransactionResponse;
import com.group3.exercise.bankapp.services.account.AccountService;
import com.group3.exercise.bankapp.services.account.AccountServiceImpl;
import com.group3.exercise.bankapp.services.transaction.TransactionStrategyNavigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private MockAccountRepository repository;
    @Mock
    private TransactionStrategyNavigator navigator;
    @Mock
    private AccountAdapter adapter;

    private AccountService service;

    @BeforeEach
    void setup(){
        this.service = new AccountServiceImpl(navigator, adapter, repository);
    }

    @Test
    @DisplayName("Should call withdraw account.")
    void shouldCallWithdrawAccountSuccessfully(){
        // given
        TransactionRequest request = new TransactionRequest();
        request.setAmount(100.0);
        request.setType("withdraw");
        InterestAccount stubReturn = new InterestAccount();
        stubReturn.setBalance(100.0);
        InterestAccount updated = new InterestAccount();
        updated.setBalance(0.0);

        when(repository.findById(anyLong())).thenReturn(stubReturn);
        when(navigator.withdraw(stubReturn, 100.0)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);
        when(adapter.mapToResponse(updated)).thenReturn(new TransactionResponse());
        // when
        TransactionResponse actual = service.withdraw(1L, request);
        // then
        verify(repository, times(1)).findById(1L);
        verify(navigator, times(1)).withdraw(stubReturn, 100.0);
        verify(repository, times(1)).save(updated);
        verify(adapter, times(1)).mapToResponse(updated);
        assertNotNull(actual);
    }
    @Test
    @DisplayName("Should call deposit account.")
    void shouldCallDepositAccountSuccessfully(){
        // given
        TransactionRequest request = new TransactionRequest();
        request.setAmount(100.0);
        request.setType("withdraw");
        InterestAccount stubReturn = new InterestAccount();
        stubReturn.setBalance(100.0);
        InterestAccount updated = new InterestAccount();
        updated.setBalance(200.0);

        when(repository.findById(anyLong())).thenReturn(stubReturn);
        when(navigator.deposit(stubReturn, 100.0)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);
        when(adapter.mapToResponse(updated)).thenReturn(new TransactionResponse());
        // when
        TransactionResponse actual = service.deposit(1L, request);
        // then
        verify(repository, times(1)).findById(1L);
        verify(navigator, times(1)).deposit(stubReturn, 100.0);
        verify(repository, times(1)).save(updated);
        verify(adapter, times(1)).mapToResponse(updated);
        assertNotNull(actual);
    }

    @Test
    @DisplayName("should throw AccountTransactionException if unable to find account")
    void shouldThrowTransactionExceptionIfUnableToFindAccount() {
        when(repository.findById(anyLong())).thenReturn(null);
        assertThrows(AccountTransactionException.class, () -> service.deposit(1L, new TransactionRequest()));
    }
    @Test
    @DisplayName("should throw AccountTransactionException if unable to map account")
    void shouldThrowTransactionExceptionIfUnableToMapAccount() {
        TransactionRequest request = new TransactionRequest();
        request.setAmount(100.0);
        when(repository.findById(anyLong())).thenReturn(new InterestAccount());
        when(navigator.deposit(any(InterestAccount.class), anyDouble())).thenReturn(new InterestAccount());
        when(adapter.mapToResponse(any(InterestAccount.class))).thenThrow(new AccountTransactionException("Unable to map response"));
        when(repository.save(any(InterestAccount.class))).thenReturn(new InterestAccount());
        AccountTransactionException actual = assertThrows(AccountTransactionException.class, () -> service.deposit(1L, request));
        assertEquals("Unable to map response", actual.getErrorMsg());
    }

}
