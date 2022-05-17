package com.example.groupexercise1.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.groupexercise1.model.dto.AccountDto;
import com.example.groupexercise1.model.dto.AccountRequestDto;
import com.example.groupexercise1.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@AutoConfigureMockMvc
public class AccountControllerTest {
	 
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 @Qualifier("regular")
	 private AccountService accountService;
	 
	 private ObjectMapper objectMapper = new ObjectMapper();

	 
	 @Test
	 @DisplayName("Should create a regular account and returns correct details and http status 200")
	 public void shouldCreateRegularAccount() throws Exception {
		 AccountDto expectedResponse = new AccountDto();
		 expectedResponse.setType("regular");
		 expectedResponse.setName("Juan Dela Cruz");
		 expectedResponse.setAcctNumber("123456");
		 expectedResponse.setMinimumBalance(500d);
			
		 AccountRequestDto accountRequest = new AccountRequestDto();
		 accountRequest.setName("Juan Dela Cruz");
		 accountRequest.setType("regular");

		 when(accountService.createAccount("Juan Dela Cruz"))
	         	.thenReturn(expectedResponse);
			
		 this.mockMvc.perform(post("/accounts").content(
		            	objectMapper.writeValueAsString(accountRequest)
		    		).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("regular"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Juan Dela Cruz"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.acctNumber").value("123456"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.minimumBalance").value("500.0"));

		  verify(accountService).createAccount("Juan Dela Cruz");
	}
}
