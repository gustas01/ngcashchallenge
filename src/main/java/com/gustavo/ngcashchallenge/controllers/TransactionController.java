package com.gustavo.ngcashchallenge.controllers;

import com.gustavo.ngcashchallenge.DTOs.DoTransactionDTO;
import com.gustavo.ngcashchallenge.models.Transaction;
import com.gustavo.ngcashchallenge.services.TokenService;
import com.gustavo.ngcashchallenge.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private TransactionService transactionService;
  private TokenService tokenService;

  public TransactionController(TransactionService transactionService, TokenService tokenService) {
    this.transactionService = transactionService;
    this.tokenService = tokenService;
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<List<Transaction>> readUsertransaction(@PathVariable long accountId){
    //retornar transações em que o usuário logado participou. Pegar o accountId do token
    return transactionService.retrieveTransaction(accountId);
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody DoTransactionDTO transactionData){
//    var token = request.getCookies() != null ? tokenService.recoverToken(request, "token") : "";
//    if(token != ""){
//
//    }
    //extrair nome do userdeCashOut do token e salvar nessa variável abaixo
//    String cashOutUserName = "";
    //tirar esse cashOutUserName vindo do DTO e pegar do token

    return transactionService.doTransaction(transactionData.cashOutUserName(), transactionData.cashInUserName(), transactionData.value());
  }
}
