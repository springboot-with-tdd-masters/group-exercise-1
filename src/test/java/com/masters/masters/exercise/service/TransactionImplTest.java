package com.masters.masters.exercise.service;

import com.masters.masters.exercise.model.Account;
import com.masters.masters.exercise.model.CheckingAccount;
import com.masters.masters.exercise.repository.AccountRepository;
import com.masters.masters.exercise.services.TransactionImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.when;
public class TransactionImplTest {

    @Mock
    AccountRepository repo;

    @InjectMocks
    TransactionImpl service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void depositCheckingAccount(){
        CheckingAccount mockRequestCa = new CheckingAccount();
        mockRequestCa.setName("ca");
        CheckingAccount mockCa = new CheckingAccount();
        mockCa.setName("ca");
        mockCa.setBalance(109.0);
        when(repo.save(Mockito.any(Account.class))).thenReturn(mockCa);
        Account updatedAccount = service.deposit(mockRequestCa,10.0);
        Assertions.assertEquals(updatedAccount.getBalance(),109.0);
    }

    @Test
    public void withdrawCheckingAccount(){
        CheckingAccount mockRequestCa = new CheckingAccount();
        mockRequestCa.setName("ca");
        CheckingAccount mockCa = new CheckingAccount();
        mockCa.setName("ca");
        mockCa.setBalance(89.0);
        when(repo.save(Mockito.any(Account.class))).thenReturn(mockCa);
        Account updatedAccount = service.withdraw(mockRequestCa,10.0);
        Assertions.assertEquals(updatedAccount.getBalance(),89.0);
    }

    @Test
    public void withdrawCheckingAccountBelowBalance(){
        CheckingAccount mockRequestCa = new CheckingAccount();
        mockRequestCa.setName("ca");
        mockRequestCa.setBalance(90);
        CheckingAccount mockCa = new CheckingAccount();
        mockCa.setName("ca");
        mockCa.setBalance(69.0);
        when(repo.save(Mockito.any(Account.class))).thenReturn(mockCa);
        Account updatedAccount = service.withdraw(mockRequestCa,10.0);
        Assertions.assertEquals(updatedAccount.getBalance(),69.0);
    }


}
