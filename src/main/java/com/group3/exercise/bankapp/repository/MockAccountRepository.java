package com.group3.exercise.bankapp.repository;

import com.group3.exercise.bankapp.entities.Account;
import org.springframework.stereotype.Repository;

/**
 *  TODO delete after JpaRepository implementation
 */
public interface MockAccountRepository {

    Account save(Account e);
    Account findById(Long id);
}
