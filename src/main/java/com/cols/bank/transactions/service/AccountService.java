package com.cols.bank.transactions.service;

import com.cols.bank.transactions.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();

    Optional<Account> findById(Long id);

    Account save(Account account);

    void delete(Long id);
}
