package com.advancejava.groupexercise1.controller;

import com.advancejava.groupexercise1.model.AccountRequest;
import com.advancejava.groupexercise1.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Save endpoint should return 200 on successful service invocation.")
    public void save() throws Exception {
        //arrange
        AccountRequest accountRequest = new AccountRequest("John Doe", "regular");
        //act
        MockHttpServletResponse response = mockMvc.perform(post("/accounts").content(objectMapper
                        .writeValueAsString(accountRequest)).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
