package com.softvision.bank.tdd.repository;

import com.softvision.bank.tdd.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static com.softvision.bank.tdd.AccountMocks.*;

@DataJpaTest
public class AccountRepositoryTests {
    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Save - should accept different accounts and save the correct respective details")
    void test_save() {
        Account savedAcct1 = accountRepository.save(getMockCheckingAccount());
        assertThat(savedAcct1)
                .extracting(Account::getBalance)
                .isEqualTo(CHK_MOCK_BALANCE);

        Account savedAcct2 = accountRepository.save(getMockRegularAccount());
        assertThat(savedAcct2)
                .extracting(Account::getBalance)
                .isEqualTo(REG_MOCK_BALANCE);
    }

    @Test
    @DisplayName("Find By ID - should accept an ID and get if it's present")
    void test_findById() {
        accountRepository.save(getMockCheckingAccount());

        Optional<Account> actualFoundAccount = accountRepository.findById(CHK_MOCK_ACCT_ID);
        if (actualFoundAccount.isPresent()) {
            assertThat(actualFoundAccount.get())
                    .extracting(Account::getBalance)
                    .isEqualTo(CHK_MOCK_BALANCE);
            assertThat(actualFoundAccount.get())
                    .extracting(Account::getId)
                    .isNotEqualTo(REG_MOCK_BALANCE);
        } else {
           fail("Account should be present");
        }
    }

    @AfterEach
    void cleanup() {
        accountRepository.deleteAll();
        assertThat(accountRepository.findAll()).isEmpty();
    }
}
