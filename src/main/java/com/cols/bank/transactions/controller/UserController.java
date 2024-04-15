package com.cols.bank.transactions.controller;

import com.cols.bank.transactions.model.User;
import com.cols.bank.transactions.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cols.bank.transactions.util.validation.FieldsValidation.validateBindingResult;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    @PreAuthorize(HAS_ROLE_ADMIN)
    @GetMapping
    public List<User> list(){
        return userService.findAll();
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if (result.hasFieldErrors()) {
            return validateBindingResult(result);
        }
        User userCreated = userService.save(user);
        if(userCreated == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario ya se encuentra registrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
