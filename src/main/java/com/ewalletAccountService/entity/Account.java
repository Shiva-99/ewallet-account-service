package com.ewalletAccountService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "username")
    private String username;

    @Column(name = "accountType")
    private String accountType;

    @Column(name = "balance")
    private double balance;
}
