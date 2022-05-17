package com.softvision.bank.tdd.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softvision.bank.tdd.AccountMocks;
import com.softvision.bank.tdd.exceptions.BadRequestException;
import com.softvision.bank.tdd.exceptions.RecordNotFoundException;
import com.softvision.bank.tdd.model.Account;
import com.softvision.bank.tdd.model.RegularAccount;
import com.softvision.bank.tdd.services.BankAccountsService;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class BankAccountsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BankAccountsService bankAccountsService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Nested
	@DisplayName("Get Account By Id Tests")
	class GetAccountByIdTests {
		@Test
		@DisplayName("Should get Regular Account by Id")
		void test_regular_getById() throws Exception {
			when(bankAccountsService.get(anyLong())).thenReturn(AccountMocks.getMockRegularAccount());

			mockMvc.perform(get("/accounts/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("type").value("regular")).andExpect(jsonPath("name").value(AccountMocks.NAME))
					.andExpect(jsonPath("acctNumber").value(AccountMocks.REG_MOCK_ACCT_NO))
					.andExpect(jsonPath("balance").value(AccountMocks.REG_MOCK_BALANCE))
					.andExpect(jsonPath("minimumBalance").value(AccountMocks.REG_MIN_BALANCE))
					.andExpect(jsonPath("penalty").value(AccountMocks.REG_PENALTY))
					.andExpect(jsonPath("transactionCharge").value(AccountMocks.REG_MOCK_TRANSACTION))
					.andExpect(jsonPath("interestCharge").value(AccountMocks.REG_MOCK_INTEREST));
		}

		@Test
		@DisplayName("Should get Checking Account by Id")
		void test_checking_getById() throws Exception {
			when(bankAccountsService.get(anyLong())).thenReturn(AccountMocks.getMockCheckingAccount());

			mockMvc.perform(get("/accounts/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("type").value("checking")).andExpect(jsonPath("name").value(AccountMocks.NAME))
					.andExpect(jsonPath("acctNumber").value(AccountMocks.CHK_MOCK_ACCT_NO))
					.andExpect(jsonPath("balance").value(AccountMocks.CHK_MOCK_BALANCE))
					.andExpect(jsonPath("minimumBalance").value(AccountMocks.CHK_MIN_BALANCE))
					.andExpect(jsonPath("penalty").value(AccountMocks.CHK_PENALTY))
					.andExpect(jsonPath("transactionCharge").value(AccountMocks.CHK_CHARGE))
					.andExpect(jsonPath("interestCharge").value(AccountMocks.CHK_MOCK_INTEREST));
		}

		@Test
		@DisplayName("Should throw RecordNotFoundException")
		void test_throw_RecordNotFoundException() throws Exception {

			when(bankAccountsService.get(anyLong())).thenThrow(RecordNotFoundException.class);
			mockMvc.perform(get("/accounts/0").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
		}

	}

	@Nested
	@DisplayName("Get Accounts")
	class GetAccountsTests {
		@Test
		@DisplayName("Should get all accounts")
		void test_get_all_accounts() throws Exception {

			List<Account> accounts = new ArrayList<>();
			accounts.add(AccountMocks.getMockRegularAccount());
			accounts.add(AccountMocks.getMockCheckingAccount());

			when(bankAccountsService.get()).thenReturn(accounts);

			mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$[0].type").value("regular"))
					.andExpect(jsonPath("$[0].name").value(AccountMocks.NAME))
					.andExpect(jsonPath("$[0].acctNumber").value(AccountMocks.REG_MOCK_ACCT_NO))
					.andExpect(jsonPath("$[0].balance").value(AccountMocks.REG_MOCK_BALANCE))
					.andExpect(jsonPath("$[0].minimumBalance").value(AccountMocks.REG_MIN_BALANCE))
					.andExpect(jsonPath("$[0].penalty").value(AccountMocks.REG_PENALTY))
					.andExpect(jsonPath("$[0].transactionCharge").value(AccountMocks.REG_MOCK_TRANSACTION))
					.andExpect(jsonPath("$[0].interestCharge").value(AccountMocks.REG_MOCK_INTEREST))
					.andExpect(jsonPath("$[1].type").value("checking"))
					.andExpect(jsonPath("$[1].name").value(AccountMocks.NAME))
					.andExpect(jsonPath("$[1].acctNumber").value(AccountMocks.CHK_MOCK_ACCT_NO))
					.andExpect(jsonPath("$[1].balance").value(AccountMocks.CHK_MOCK_BALANCE))
					.andExpect(jsonPath("$[1].minimumBalance").value(AccountMocks.CHK_MIN_BALANCE))
					.andExpect(jsonPath("$[1].penalty").value(AccountMocks.CHK_PENALTY))
					.andExpect(jsonPath("$[1].transactionCharge").value(AccountMocks.CHK_CHARGE))
					.andExpect(jsonPath("$[1].interestCharge").value(AccountMocks.CHK_MOCK_INTEREST));
		}

		@Test
		@DisplayName("Should return empty list when no record found")
		void test_get_all_accounts_empty_list() throws Exception {

			when(bankAccountsService.get()).thenReturn(new ArrayList<Account>());

			MvcResult mvcResult = mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();

			assertThat("[]").isEqualTo(mvcResult.getResponse().getContentAsString());
		}
	}

	@Nested
	@DisplayName("Create Account")
	class CreateAccountTests {
		@Test
		@DisplayName("Should create regular account")
		void test_create_regular_account() throws Exception {

			when(bankAccountsService.createUpdate(any())).thenReturn(AccountMocks.getMockRegularAccount());

			mockMvc.perform(
					post("/accounts").content(objectMapper.writeValueAsString(AccountMocks.getMockRegularAccount()))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(jsonPath("type").value("regular"))
					.andExpect(jsonPath("name").value(AccountMocks.NAME))
					.andExpect(jsonPath("acctNumber").value(AccountMocks.REG_MOCK_ACCT_NO))
					.andExpect(jsonPath("balance").value(AccountMocks.REG_MOCK_BALANCE))
					.andExpect(jsonPath("minimumBalance").value(AccountMocks.REG_MIN_BALANCE))
					.andExpect(jsonPath("penalty").value(AccountMocks.REG_PENALTY))
					.andExpect(jsonPath("transactionCharge").value(AccountMocks.REG_MOCK_TRANSACTION))
					.andExpect(jsonPath("interestCharge").value(AccountMocks.REG_MOCK_INTEREST));

		}

		@Test
		@DisplayName("Should create regular account")
		void test_create_checking_account() throws Exception {

			when(bankAccountsService.createUpdate(any())).thenReturn(AccountMocks.getMockCheckingAccount());

			mockMvc.perform(
					post("/accounts").content(objectMapper.writeValueAsString(AccountMocks.getMockRegularAccount()))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(jsonPath("type").value("checking"))
					.andExpect(jsonPath("name").value(AccountMocks.NAME))
					.andExpect(jsonPath("acctNumber").value(AccountMocks.CHK_MOCK_ACCT_NO))
					.andExpect(jsonPath("balance").value(AccountMocks.CHK_MOCK_BALANCE))
					.andExpect(jsonPath("minimumBalance").value(AccountMocks.CHK_MIN_BALANCE))
					.andExpect(jsonPath("penalty").value(AccountMocks.CHK_PENALTY))
					.andExpect(jsonPath("transactionCharge").value(AccountMocks.CHK_CHARGE))
					.andExpect(jsonPath("interestCharge").value(AccountMocks.CHK_MOCK_INTEREST));

		}

		@Test
		@DisplayName("Should throw bad requst exception ")
		void test_throw_BadRequestException() throws Exception {

			when(bankAccountsService.createUpdate(any())).thenThrow(BadRequestException.class);
			mockMvc.perform(post("/accounts").content(objectMapper.writeValueAsString(new RegularAccount()))
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		}
	}

}
