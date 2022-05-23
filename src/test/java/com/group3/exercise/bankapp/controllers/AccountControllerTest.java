package com.group3.exercise.bankapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.exercise.bankapp.exceptions.BankAppException;
import com.group3.exercise.bankapp.exceptions.BankAppExceptionCode;
import com.group3.exercise.bankapp.exceptions.GlobalExceptionHandler;
import com.group3.exercise.bankapp.request.CreateAccountRequest;
import com.group3.exercise.bankapp.response.AccountResponse;
import com.group3.exercise.bankapp.services.account.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers =  AccountController.class)
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    MockMvc mvc;
    @MockBean
    AccountService service;

    AccountController controller;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setup(){
        controller = new AccountController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    @DisplayName("should return registered account")
    void shouldReturnRegisteredInterestAccount() throws Exception {
        // given
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("Jane Doe");
        request.setType("interest");

        AccountResponse response = new AccountResponse();
        response.setBalance(100.0);
        response.setMinimumBalance(0.0);
        response.setInterestCharge(0.03);
        response.setTransactionCharge(0.0);
        response.setId(1L);
        response.setPenalty(0.0);
        response.setAcctNumber("123456789");
        response.setName("JOAN DOE");

        when(service.register(any(CreateAccountRequest.class))).thenReturn(response);
        // when
        ResultActions result = mvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)));

        // then
        verify(service, times(1)).register(any(CreateAccountRequest.class));
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.name", is("JOAN DOE")));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.acctNumber", is("123456789")));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));

    }
    @Test
    @DisplayName("should return 400")
    void shouldReturnBadRequestForNonExistingAccountType() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("Jane Doe");
        request.setType("credit");
        when(service.register(any(CreateAccountRequest.class))).thenThrow(new BankAppException(BankAppExceptionCode.BAD_REQUEST));
        // when
        ResultActions result = mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)));

        // then
        verify(service, times(1)).register(any(CreateAccountRequest.class));
        result.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.error", is("Invalid Account Type")));
    }
    @Test
    @DisplayName("should return 500")
    void shouldReturnInternalServerErrorForUnableToCreateAccount() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setName("Jane Doe");
        request.setType("interest");
        when(service.register(any(CreateAccountRequest.class))).thenThrow(new BankAppException(BankAppExceptionCode.INTERNAL_SERVER_ERROR));
        // when
        ResultActions result = mvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)));

        // then
        verify(service, times(1)).register(any(CreateAccountRequest.class));
        result.andExpect(MockMvcResultMatchers.status().is5xxServerError());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.error", is("Unable to process transaction")));
    }


}
