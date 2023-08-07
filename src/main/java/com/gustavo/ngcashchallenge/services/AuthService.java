package com.gustavo.ngcashchallenge.services;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(rollbackFor = { SQLException.class })
public class AuthService {
  private UserMapper userMapper;
  private AccountRepository accountRepository;
  private UserRepository userRepository;

  public AuthService(UserMapper userMapper, AccountRepository accountRepository, UserRepository userRepository) {
    this.userMapper = userMapper;
    this.accountRepository = accountRepository;
    this.userRepository = userRepository;
  }

  public ResponseEntity<String> register(CreateUserDTO userDTO){
    User user = userMapper.CreateUserDTOtoUser(userDTO);
    Account account = new Account();
    account.setBalance(100);
//    account = accountRepository.save(account);
    user.setAccount(account);
    userRepository.save(user);
    return ResponseEntity.ok("Usuário " + userDTO.name() + " criado com sucesso!");
  }
}
