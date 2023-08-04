package com.gustavo.ngcashchallenge.services;

import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

  private AccountRepository accountRepository;
  private UserRepository userRepository;
  private TransactionService transactionService;

  public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionService transactionService) {
    this.accountRepository = accountRepository;
    this.userRepository = userRepository;
    this.transactionService = transactionService;
  }

  public ResponseEntity<Account> create(Account account){
    return ResponseEntity.ok(accountRepository.save(account));
  }

  public ResponseEntity<String> doTransaction(String cashOutUserName, String cashInUserName, double value){
    User userCashOut = userRepository.findByusername(cashOutUserName);
    User userCashIn = userRepository.findByusername(cashInUserName);

    Optional<Account> accountUserCashOut = accountRepository.findById(userCashOut.getAccount().getId());
    accountUserCashOut.get().setBalance(accountUserCashOut.get().getBalance() - value);
    userRepository.save(userCashOut);

    Optional<Account> accountUserCashIn = accountRepository.findById(userCashIn.getAccount().getId());
    accountUserCashIn.get().setBalance(accountUserCashIn.get().getBalance() + value);
    userRepository.save(userCashIn);

    transactionService.create(userCashOut.getAccount().getId(), userCashIn.getAccount().getId(), value);

    return ResponseEntity.ok("Transação efetuada com sucesso");
  }
}
