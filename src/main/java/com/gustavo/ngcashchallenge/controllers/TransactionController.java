package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.DoTransactionDTO;
import com.gustavo.ngcashchallenge.services.TokenService;
import com.gustavo.ngcashchallenge.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private TransactionService transactionService;
  private TokenService tokenService;

  public TransactionController(TransactionService transactionService, TokenService tokenService) {
    this.transactionService = transactionService;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody DoTransactionDTO transactionData){
//    var token = request.getCookies() != null ? tokenService.recoverToken(request, "token") : "";
//    if(token != ""){
//
//    }
    //extrair nome do userdeCashOut do token e salvar nessa variável abaixo
//    String cashOutUserName = "";

    return transactionService.doTransaction(transactionData.cashOutUserName(), transactionData.cashInUserName(), transactionData.value());
  }
}
