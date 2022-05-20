package com.softvision.bank.tdd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softvision.bank.tdd.services.BankAccountsService;
import com.softvision.bank.tdd.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * created this parent abstract class to avoid duplicating
 * fields to all controller tests - as MockMvc requires
 * all mocks from all controllers be defined.
 */

public abstract class AbstractControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BankAccountsService bankAccountsService;
    @MockBean
    TransactionService transactionService;

    static final ObjectMapper objectMapper = new ObjectMapper();
}
