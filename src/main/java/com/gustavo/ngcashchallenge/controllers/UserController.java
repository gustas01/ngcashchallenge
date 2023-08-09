package com.gustavo.ngcashchallenge.controllers;

import com.auth0.jwt.interfaces.Claim;
import com.gustavo.ngcashchallenge.DTOs.ReadUserDTO;
import com.gustavo.ngcashchallenge.services.TokenService;
import com.gustavo.ngcashchallenge.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;
  private TokenService tokenService;

  public UserController(UserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @GetMapping
  public ResponseEntity<ReadUserDTO> read(){
    Map<String, Claim> jwtData = tokenService.retrieveDataFromToken("token");
    String username = jwtData.get("username").asString();
    return userService.read(username);
  }

  @PutMapping
  public String update(){
    return "Usuário atualizado com sucesso";
  }

  @DeleteMapping
  public String delete(){
    return "Usuário deletado com sucesso";
  }
}

