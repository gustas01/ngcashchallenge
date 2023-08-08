package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.ReadUserDTO;
import com.gustavo.ngcashchallenge.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{username}")
  public ResponseEntity<ReadUserDTO> read(@PathVariable String username){
    //tirar esse username dos params e pegar do token
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

