package com.gustavo.ngcashchallenge.services;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(rollbackFor = { SQLException.class })
public class AuthService {
  private UserMapper userMapper;
  private AccountRepository accountRepository;
  private UserRepository userRepository;
  private TokenService tokenService;

  public AuthService(UserMapper userMapper, AccountRepository accountRepository, UserRepository userRepository, TokenService tokenService) {
    this.userMapper = userMapper;
    this.accountRepository = accountRepository;
    this.userRepository = userRepository;
    this.tokenService = tokenService;
  }

  public ResponseEntity<String> register(CreateUserDTO userDTO){
    User user = userMapper.CreateUserDTOtoUser(userDTO);
    Account account = new Account();
    account.setBalance(100);
//    account = accountRepository.save(account);
    user.setAccount(account);

    user.setPassword(new BCryptPasswordEncoder().encode(userDTO.password()));
    userRepository.save(user);
    return ResponseEntity.ok("Usuário " + userDTO.name() + " criado com sucesso!");
  }

  public ResponseEntity<String> login(User user){
    tokenService.generateToken(user);
    return ResponseEntity.ok("Usuário logado com sucesso!");
  }
}
