package com.cols.bank.transactions.service;

import com.cols.bank.transactions.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);
}
