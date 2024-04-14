package com.cols.bank.transactions.model;

import com.cols.bank.transactions.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.transaction.type}")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @NotNull(message = "{NotNull.transaction.date}")
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull(message = "{NotNull.transaction.amount}")
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull(message = "{NotNull.transaction.initialBalance}")
    @Column(name = "initial_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal initialBalance;

    @NotNull(message = "{NotNull.transaction.finalBalance}")
    @Column(name = "final_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalBalance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
