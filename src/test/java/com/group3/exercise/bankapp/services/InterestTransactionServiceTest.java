package com.group3.exercise.bankapp.services;


import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InterestTransactionServiceTest {

    InterestTransactionService interestTransactionService;

    @BeforeEach
    void setup(){
    interestTransactionService = new InterestTransactionService(0.03, 0.0);
    }


    @Test
    @DisplayName("Should generate correct details Interest Account")
    void shouldGenerateCorrectAccountDetails(){
        // given
        String name = "MARIO A";
        String acctNbr = "12343555";
        // when
        Account actual = interestTransactionService.generateNewAccountDetails(name, acctNbr);
        // then
        assertEquals(actual.getInterestCharge(), 0.03);
        assertEquals(actual.getMinimumBalance(), 0.0);
    }
    @Test
    @DisplayName("Should withdraw from account")
    void shouldWithdrawFromAccount(){
        // given
        Account stub = new InterestAccount();
        stub.setBalance(0.0);
        // when
        Account actual = interestTransactionService.withdraw(stub, 200.0);
        // then
        assertEquals(-200.0, actual.getBalance());
    }
    @Test
    @DisplayName("Should deposit to account")
    void shouldDepositToAccount(){
        // given
        Account stub = new InterestAccount();
        stub.setBalance(0.0);
        // when
        Account actual = interestTransactionService.deposit(stub, 200.0);
        // then
        assertEquals(200.0, actual.getBalance());
    }
}
