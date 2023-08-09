package com.gustavo.ngcashchallenge.controllers;

import com.auth0.jwt.interfaces.Claim;
import com.gustavo.ngcashchallenge.DTOs.DoTransactionDTO;
import com.gustavo.ngcashchallenge.models.Transaction;
import com.gustavo.ngcashchallenge.services.TokenService;
import com.gustavo.ngcashchallenge.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private TransactionService transactionService;
  private TokenService tokenService;
  private HttpServletRequest httpServletRequest;

  public TransactionController(TransactionService transactionService, TokenService tokenService, HttpServletRequest httpServletRequest) {
    this.transactionService = transactionService;
    this.tokenService = tokenService;
    this.httpServletRequest = httpServletRequest;
  }

  @GetMapping
  public ResponseEntity<List<Transaction>> readUsertransaction(
          @RequestParam(required = false, defaultValue = "false") String cashInFilter,
          @RequestParam(required = false, defaultValue = "false") String cashOutFilter,
          @RequestParam(required = false) String dateFilter
  ){
    Map<String, Claim> jwtData = tokenService.retrieveDataFromToken("token");
    Long accountId = Long.parseLong(jwtData.get("accountId").asString());
    return transactionService.retrieveTransaction(accountId, cashInFilter, cashOutFilter, dateFilter);
  }



  @PostMapping
  public ResponseEntity<String> create(@RequestBody DoTransactionDTO transactionData){
    Map<String, Claim> jwtData = tokenService.retrieveDataFromToken("token");
    String cashOutUserName = jwtData.get("username").asString();

    return transactionService.doTransaction(cashOutUserName, transactionData.cashInUserName(), transactionData.value());
  }
}
