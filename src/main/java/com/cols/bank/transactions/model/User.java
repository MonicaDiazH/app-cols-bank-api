package com.cols.bank.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{NotBlank.user.username}")
    @Size(min = 4, message = "{Size.user.username}")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "{NotBlank.user.password}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @PrePersist
    public void prePersist(){
        enabled = true;
    }

    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) }
    )
    private List<Role> roles;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;
}
