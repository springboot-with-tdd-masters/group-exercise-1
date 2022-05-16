package com.group3.exercise.bankapp.adapters;


import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import com.group3.exercise.bankapp.exceptions.AccountTransactionException;
import com.group3.exercise.bankapp.request.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountAdapterTest {

    private AccountAdapter adapter;

    @BeforeEach
    void setup(){
        adapter = new AccountAdapter();
    }

    @Test
    @DisplayName("should map to transaction response successfully")
    void shouldMapToTransactionResponseSuccessfully() {
        // given
        Account account = new InterestAccount();
        account.setBalance(100.0);
        account.setMinimumBalance(0.0);
        account.setInterestCharge(0.03);
        account.setTransactionCharge(0.0);
        account.setId(1L);
        account.setPenalty(0.0);
        account.setAcctNumber("123456789");
        // when
        TransactionResponse actual = adapter.mapToResponse(account);
        // then
        assertEquals(100.0, actual.getBalance());
        assertEquals(0.0, actual.getMinimumBalance());
        assertEquals(0.03, actual.getInterestCharge());
        assertEquals(0.0, actual.getTransactionCharge());
        assertEquals(1L, actual.getId());
        assertEquals(0.0, actual.getPenalty());
        assertEquals("123456789", account.getAcctNumber());
    }
    @Test
    @DisplayName("should throw InvalidAccountTransactionException on null object")
    void shouldThrowInvalidAccountTransactionExceptionNull(){
        AccountTransactionException ex = assertThrows(AccountTransactionException.class, () -> adapter.mapToResponse(null));
        assertEquals("Unable to map response", ex.getErrorMsg());
    }
}
