package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import com.gustavo.ngcashchallenge.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response){
    //TODO: gerar token chamando método de tokenService e setando ele no cookie
    Cookie cookie = new Cookie("token", "umjwtaleatorio");
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    response.addCookie(cookie);
    return ResponseEntity.ok("LOGIN");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody CreateUserDTO userDTO){
    return authService.register(userDTO);
  }
}
