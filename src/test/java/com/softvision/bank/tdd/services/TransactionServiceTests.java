package com.softvision.bank.tdd.services;

import com.softvision.bank.tdd.model.Account;
import com.softvision.bank.tdd.model.Transaction;
import com.softvision.bank.tdd.model.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.softvision.bank.tdd.repository.AccountRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.softvision.bank.tdd.AccountMocks.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

    @Mock
    AccountRepository mockedAccountRepository;
    @InjectMocks
    TransactionService transactionService = new TransactionServiceImpl();

    @Captor
    ArgumentCaptor<Account> saveAccountCaptor;

    @Test
    @DisplayName("Account - Should add account deposited")
    void test_deposit() {
        //setup mocked returns
        Account mockedRegularAccount = getMockRegularAccount();
        when(mockedAccountRepository.findById(REG_MOCK_ACCT_ID)).thenReturn(Optional.of(mockedRegularAccount));
        when(mockedAccountRepository.save(saveAccountCaptor.capture())).thenReturn(mockedRegularAccount);

        //run
        Account actualAccount = transactionService.transact(REG_MOCK_ACCT_ID,
                new Transaction("deposit", 100));

        //check argument passed at save call
        assertEquals((int) saveAccountCaptor.getValue().getBalance(), 1600);
        //check returned account
        assertEquals((int) actualAccount.getBalance(), 1600);
    }

    @Test
    @DisplayName("Regular Account - Should subtract amount withdrawn")
    void test_withdraw_regularAcct() {
        //setup mocked returns
        Account mockedRegularAccount = getMockRegularAccount();
        when(mockedAccountRepository.findById(REG_MOCK_ACCT_ID)).thenReturn(Optional.of(mockedRegularAccount));
        when(mockedAccountRepository.save(saveAccountCaptor.capture())).thenReturn(mockedRegularAccount);

        //run
        Account actualAccount = transactionService.transact(REG_MOCK_ACCT_ID,
                new Transaction("withdraw", 100));

        //check argument passed at save call
        assertEquals((int) saveAccountCaptor.getValue().getBalance(), 1400);
        //check returned account
        assertEquals((int) actualAccount.getBalance(), 1400);
    }

    @Test
    @DisplayName("Regular Account - Should subtract amount withdrawn with penalties")
    void test_withdraw_regularAcct_withPenalties() {
        //setup mocked returns
        Account mockedRegularAccount = getMockRegularAccount();
        mockedRegularAccount.setBalance(500);
        when(mockedAccountRepository.findById(REG_MOCK_ACCT_ID)).thenReturn(Optional.of(mockedRegularAccount));
        when(mockedAccountRepository.save(saveAccountCaptor.capture())).thenReturn(mockedRegularAccount);

        //run
        Account actualAccount = transactionService.transact(REG_MOCK_ACCT_ID,
                new Transaction("withdraw", 100));

        //check argument passed at save call
        assertEquals((int) saveAccountCaptor.getValue().getBalance(), 390);
        //check returned account
        assertEquals((int) actualAccount.getBalance(), 390);
    }


    @Test
    @DisplayName("Checking Account- Should subtract amount withdrawn")
    void test_withdraw_checking() {
        //setup mocked returns
        Account mockedCheckingAccount = getMockCheckingAccount();
        when(mockedAccountRepository.findById(CHK_MOCK_ACCT_ID)).thenReturn(Optional.of(mockedCheckingAccount));
        when(mockedAccountRepository.save(saveAccountCaptor.capture())).thenReturn(mockedCheckingAccount);

        //run
        Account actualAccount = transactionService.transact(CHK_MOCK_ACCT_ID, new Transaction("withdraw", 100));

        //check argument passed at save call
        assertEquals((int) saveAccountCaptor.getValue().getBalance(), 899);
        //check returned account
        assertEquals((int)actualAccount.getBalance(), 899);
    }

    @Test
    @DisplayName("Checking Account- Should subtract amount withdrawn")
    void test_withdraw_withPenalties() {
        //setup mocked returns
        Account mockedCheckingAccount = getMockCheckingAccount();
        mockedCheckingAccount.setBalance(90);
        when(mockedAccountRepository.findById(CHK_MOCK_ACCT_ID)).thenReturn(Optional.of(mockedCheckingAccount));
        when(mockedAccountRepository.save(saveAccountCaptor.capture())).thenReturn(mockedCheckingAccount);

        //run
        Account actualAccount = transactionService.transact(CHK_MOCK_ACCT_ID, new Transaction("withdraw", 50));

        //check argument passed at save call
        assertEquals((int) saveAccountCaptor.getValue().getBalance(), 29);
        //check returned account
        assertEquals((int)actualAccount.getBalance(), 29);
    }

    @Test
    @DisplayName("Account - Should throw RecordNotFoundException when account is not found in repo")
    void test_transaction_fail_throwException_RecordNotFoundException() {
        when(mockedAccountRepository.findById(CHK_MOCK_ACCT_ID)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () ->
                transactionService.transact(CHK_MOCK_ACCT_ID, new Transaction()));
    }

    @Test
    @DisplayName("Account - Should throw IllegalArgumentException on bad request/parameters")
    void test_transaction_fail_throwException_IllegalArgumentException() {
        Account mockedCheckingAccount = getMockCheckingAccount();
        when(mockedAccountRepository.findById(CHK_MOCK_ACCT_ID)).thenReturn(Optional.of(mockedCheckingAccount));

        assertThrows(IllegalArgumentException.class, () ->
                transactionService.transact(CHK_MOCK_ACCT_ID, new Transaction("garbage transaction", 100)));
        assertThrows(IllegalArgumentException.class, () ->
                transactionService.transact(CHK_MOCK_ACCT_ID, new Transaction(null, 100)));
    }
}
