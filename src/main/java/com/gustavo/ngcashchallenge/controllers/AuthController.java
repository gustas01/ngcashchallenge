package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private UserRepository userRepository;
  private AccountRepository accountRepository;
  private UserMapper userMapper;


  public AuthController(UserRepository userRepository, AccountRepository accountRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
    this.userMapper = userMapper;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(){
    return ResponseEntity.ok("");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody CreateUserDTO userDTO){
    //TODO: criar um service para colocar essa lógica
    User user = userMapper.CreateUserDTOtoUser(userDTO);
    Account account = accountRepository.save(new Account());
    account.setBalance(100);
    user.setAccount(account);
    userRepository.save(user);
    return ResponseEntity.ok("Usuário " + userDTO.name() + " criado com sucesso!");
  }
}
