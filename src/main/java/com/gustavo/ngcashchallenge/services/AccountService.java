package com.gustavo.ngcashchallenge.services;

import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionService transactionService) {
    this.accountRepository = accountRepository;
  }

  //posso chamar isso daqui quando fizer o read de user
  public ResponseEntity<Account> read(long id){
    return ResponseEntity.ok(accountRepository.findById(id).get());
  }


}
