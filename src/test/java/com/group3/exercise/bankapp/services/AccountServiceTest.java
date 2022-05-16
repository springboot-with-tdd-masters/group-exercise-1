package com.group3.exercise.bankapp.services;

import com.group3.exercise.bankapp.adapters.AccountAdapter;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.repository.MockAccountRepository;
import com.group3.exercise.bankapp.request.TransactionRequest;
import com.group3.exercise.bankapp.request.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private MockAccountRepository repository;
    @Mock
    private TransactionServiceFactory factory;
    @Mock
    private AccountAdapter adapter;

    private AccountService service;

    @BeforeEach
    void setup(){
        this.service = new AccountServiceImpl(factory, adapter, repository);
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
        when(factory.withdraw(stubReturn, 100.0)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);
        when(adapter.mapToResponse(updated)).thenReturn(new TransactionResponse());
        // when
        TransactionResponse actual = service.withdraw(1L, request);
        // then
        verify(repository, times(1)).findById(1L);
        verify(factory, times(1)).withdraw(stubReturn, 100.0);
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
        when(factory.deposit(stubReturn, 100.0)).thenReturn(updated);
        when(repository.save(updated)).thenReturn(updated);
        when(adapter.mapToResponse(updated)).thenReturn(new TransactionResponse());
        // when
        TransactionResponse actual = service.deposit(1L, request);
        // then
        verify(repository, times(1)).findById(1L);
        verify(factory, times(1)).deposit(stubReturn, 100.0);
        verify(repository, times(1)).save(updated);
        verify(adapter, times(1)).mapToResponse(updated);
        assertNotNull(actual);
    }

}
