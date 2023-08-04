package com.gustavo.ngcashchallenge.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping
  public String read(){
    return "user read";
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
