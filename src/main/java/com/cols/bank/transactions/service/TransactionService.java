package com.cols.bank.transactions.service;

import com.cols.bank.transactions.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findAll();

    Optional<Transaction> findById(Long id);

    Transaction save(Transaction client);

    void delete(Long id);
}
