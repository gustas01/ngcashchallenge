package com.gustavo.ngcashchallenge.models;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private double balance;
}
