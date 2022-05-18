package com.masters.masters.exercise.repository;

import com.masters.masters.exercise.model.CheckingAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository repo;

    @Test
    public void saveCheckingAccountHappyPath(){
        CheckingAccount account = new CheckingAccount();
        account.setName("name");
        CheckingAccount newAccount = repo.save(account);
        Assertions.assertThat(newAccount).extracting("name","minimumBalance","penalty","transactionCharge","balance")
                .containsExactly("name",100.0,10.0,1.0,100.0);
    }

    @Test
    public void updateCheckingAccountHappyPath(){
        CheckingAccount account = new CheckingAccount();
        account.setName("name");
        account.setMinimumBalance(100);
        account.setPenalty(10);
        account.setTransactionCharge(1);
        CheckingAccount newAccount = repo.save(account);
        Assertions.assertThat(newAccount).extracting("name","minimumBalance","penalty","transactionCharge","balance")
                .containsExactly("name",100.0,10.0,1.0,100.0);
        newAccount.setBalance(90.0);
        CheckingAccount updatedAccount = repo.save(newAccount);
        Assertions.assertThat(updatedAccount).extracting("name","minimumBalance","penalty","transactionCharge","balance")
                .containsExactly("name",100.0,10.0,1.0,90.0);
    }
}
