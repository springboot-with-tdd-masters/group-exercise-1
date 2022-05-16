package com.group3.exercise.bankapp.services.impl;


import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.entities.InterestAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
        String type = "interest";
        // when
        Account actual = interestTransactionService.generateNewAccountDetails(name, acctNbr, type);
        // then
        assertEquals(actual.getInterestCharge(), 0.03);
        assertEquals(actual.getMinimumBalance(), 0.0);
    }
    @Test
    @DisplayName("Should generate correct details Interest Account")
    void shouldWithdrawFromAccount(){

    }
    @Test
    @DisplayName("Should generate correct details Interest Account")
    void shouldDepositToAccount(){

    }
}
