package com.softvision.bank.tdd.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softvision.bank.tdd.AccountMocks;
import com.softvision.bank.tdd.exceptions.BadRequestException;
import com.softvision.bank.tdd.model.Transaction;
import com.softvision.bank.tdd.repository.AccountRepository;
import com.softvision.bank.tdd.services.BankAccountsService;
import com.softvision.bank.tdd.services.TransactionService;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	AccountRepository accountRepository;

	@MockBean
	private BankAccountsService bankAccountsService;

	@MockBean
	private TransactionService trasactionService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Nested
	@DisplayName("Should Deposit/Withdraw to Account")
	class Deposit {
		@Test
		@DisplayName("Regular Account")
		void test_regular_account_deposit() throws Exception {
			when(trasactionService.transact(anyLong(), any())).thenReturn(AccountMocks.getMockRegularAccount());

			mockMvc.perform(post("/accounts/1/transactions")
					.content(objectMapper.writeValueAsString(new Transaction(anyString(), anyLong())))
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("type").value("regular"))
					.andExpect(jsonPath("balance").value(AccountMocks.REG_MOCK_BALANCE));
		}
		
		@Test
		@DisplayName("Checking Account")
		void test_checking_account_deposit() throws Exception {
			when(trasactionService.transact(anyLong(), any())).thenReturn(AccountMocks.getMockCheckingAccount());

			mockMvc.perform(post("/accounts/1/transactions")
					.content(objectMapper.writeValueAsString(new Transaction(anyString(), anyLong())))
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("type").value("checking"))
					.andExpect(jsonPath("balance").value(AccountMocks.CHK_MOCK_BALANCE));
		}
		
		@Test
		@DisplayName("Interest Account")
		void test_regular_interest_deposit() throws Exception {
			when(trasactionService.transact(anyLong(), any())).thenReturn(AccountMocks.getMockInterestAccount());

			mockMvc.perform(post("/accounts/1/transactions")
					.content(objectMapper.writeValueAsString(new Transaction(anyString(), anyLong())))
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("type").value("interest"))
					.andExpect(jsonPath("balance").value(AccountMocks.INT_MOCK_BALANCE));
		}
		
		@Test
		@DisplayName("Should throw BadRequestException")
		void test_throw_BadRequestException() throws Exception {

			when(trasactionService.transact(anyLong(), any())).thenThrow(BadRequestException.class);
			mockMvc.perform(post("/accounts/1/transactions").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
		}
	}	
	
}
