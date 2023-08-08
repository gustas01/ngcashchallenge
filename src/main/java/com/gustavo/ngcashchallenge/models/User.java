package com.gustavo.ngcashchallenge.models;

import com.gustavo.ngcashchallenge.ENUMs.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Length(min = 3, max = 50)
  @Column(nullable = false)
  private String name;

  @Length(min = 3, max = 50)
  @Column(nullable = false, unique = true)
  private String username;

  @Length(min = 8)
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Account account = new Account();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return switch (this.role){
      case USER -> List.of(new SimpleGrantedAuthority("USER"));
      case ADMIN -> List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));
    };
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
