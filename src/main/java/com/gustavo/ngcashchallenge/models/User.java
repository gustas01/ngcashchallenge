package com.gustavo.ngcashchallenge.models;

import com.gustavo.ngcashchallenge.ENUMs.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Length(min = 3, max = 50)
  @Column(nullable = false)
  private String name;

  @Length(min = 5, max = 50)
  @Column(nullable = false, unique = true)
  private String username;

  @Length(min = 5)
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Account accountId = new Account();
}
