package com.cols.bank.transactions.service;

import com.cols.bank.transactions.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client save(Client client);

    Optional<Client> update(Long id, Client client);

    void delete(Long id);
}
