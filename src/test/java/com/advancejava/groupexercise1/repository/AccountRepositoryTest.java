package com.advancejava.groupexercise1.repository;

import com.advancejava.groupexercise1.entity.RegularAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSave() {
        //arrange
       // RegularAccount regularAccount = new RegularAccount("John Doe");

        //execute
   //     RegularAccount savedAccount = accountRepository.save(regularAccount);

        //test
//        assertThat(savedAccount)
//                .extracting("name", "minimumBalance")
//                .containsExactly("John Doe", 500.00);
    }
}
