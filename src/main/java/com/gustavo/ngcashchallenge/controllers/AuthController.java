package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public AuthController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(){
    return ResponseEntity.ok("");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(CreateUserDTO user){
    userRepository.save(userMapper.DTOtoUser(user));


    return ResponseEntity.ok("");
  }
}
