package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.DTOs.LoginUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import com.gustavo.ngcashchallenge.services.AuthService;
import com.gustavo.ngcashchallenge.services.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private AuthService authService;
  private UserRepository userRepository;
  private AuthenticationManager authenticationManager;

  public AuthController(AuthService authService, UserRepository userRepository, AuthenticationManager authenticationManager) {
    this.authService = authService;
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginUserDTO loginUserDTO){
    User user = userRepository.findByusername(loginUserDTO.username());
    if(user == null) return new ResponseEntity("Usuário não encontrado", HttpStatus.BAD_REQUEST);

    Authentication usernamePassword = new UsernamePasswordAuthenticationToken(loginUserDTO.username(), loginUserDTO.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    authService.login(user);

    return ResponseEntity.ok("LOGIN REALIZADO COM SUCESSO");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody CreateUserDTO userDTO){
    return authService.register(userDTO);
  }
}
