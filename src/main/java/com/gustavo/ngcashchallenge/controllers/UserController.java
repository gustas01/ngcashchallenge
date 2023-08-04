package com.gustavo.ngcashchallenge.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @PostMapping
  public String create(){
    return "Usuário criado com sucesso";
  }
}
