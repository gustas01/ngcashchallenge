package com.gustavo.ngcashchallenge.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long debitedAccountId;

    @Column(nullable = false)
    private long creditedAccountId;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
