package com.cols.bank.transactions.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.client.firstName}")
    @Size(min=3, max=20, message = "{Size.client.firstName}")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "{NotBlank.client.lastName}")
    @Size(min=3, max=20, message = "{Size.client.lastName}")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "second_last_name")
    private String secondLastName;

    @NotBlank(message = "{NotBlank.client.address}")
    @Size(min=5, message = "{Size.client.address}")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "{NotBlank.client.phone}")
    @Size(min=8, max=8, message = "{Size.client.phone}")
    @Column(name = "phone", length = 8)
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;
}
