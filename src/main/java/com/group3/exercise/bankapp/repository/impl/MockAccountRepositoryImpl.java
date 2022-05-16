package com.group3.exercise.bankapp.repository.impl;

import com.group3.exercise.bankapp.entities.Account;
import com.group3.exercise.bankapp.repository.MockAccountRepository;
import org.springframework.stereotype.Repository;

/** TODO delete after JpaRepository is implemented
 *
 */
@Repository
public class MockAccountRepositoryImpl implements MockAccountRepository {
    @Override
    public Account save(Account e) {
        return e;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }
}
