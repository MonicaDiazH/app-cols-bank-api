package com.cols.bank.transactions.service.impl;

import com.cols.bank.transactions.model.Client;
import com.cols.bank.transactions.repository.ClientRepository;
import com.cols.bank.transactions.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> update(Long id, Client client) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setFirstName(client.getFirstName());
            existingClient.setMiddleName(client.getMiddleName());
            existingClient.setLastName(client.getLastName());
            existingClient.setSecondLastName(client.getSecondLastName());
            existingClient.setAddress(client.getAddress());
            existingClient.setPhone(client.getPhone());
            return clientRepository.save(existingClient);
        });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
