package com.group3.exercise.bankapp.repositories;

import com.group3.exercise.bankapp.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAcctNumber(String acctNumber);

}
