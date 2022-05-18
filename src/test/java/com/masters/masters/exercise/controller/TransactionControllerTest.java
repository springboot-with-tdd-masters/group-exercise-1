package com.masters.masters.exercise.controller;

import com.masters.masters.exercise.services.AccountServiceImpl;
import com.masters.masters.exercise.services.TransactionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionImpl transactionService;

    @MockBean
    private AccountServiceImpl accountService;

}
