package com.gustavo.ngcashchallenge.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long debitedAccountId;

    @Column(nullable = false)
    private long creditedAccountId;

    @Column(nullable = false)
    private long value;

    @Column(nullable = false)
    private Date createdAt;
}
