package com.cols.bank.transactions.util.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class FieldsValidation {

    private FieldsValidation() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static ResponseEntity<?> validateBindingResult(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }
}
