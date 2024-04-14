package com.cols.bank.transactions.service.impl;

import com.cols.bank.transactions.model.Role;
import com.cols.bank.transactions.model.User;
import com.cols.bank.transactions.repository.RoleRepository;
import com.cols.bank.transactions.repository.UserRepository;
import com.cols.bank.transactions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> roleUserOpt = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        roleUserOpt.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> roleAdminOpt = roleRepository.findByName("ROLE_ADMIN");
            roleAdminOpt.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
