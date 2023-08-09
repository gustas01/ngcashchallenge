package com.gustavo.ngcashchallenge.services;

import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.Transaction;
import com.gustavo.ngcashchallenge.models.User;
import com.gustavo.ngcashchallenge.repositories.AccountRepository;
import com.gustavo.ngcashchallenge.repositories.TransactionRepository;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = { SQLException.class })
public class TransactionService {
  private AccountRepository accountRepository;
  private UserRepository userRepository;
  private TransactionRepository transactionRepository;

  public TransactionService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
  }

  public ResponseEntity<String> doTransaction(String cashOutUserName, String cashInUserName, double value){
    User userCashOut = userRepository.findByusername(cashOutUserName);
    User userCashIn = userRepository.findByusername(cashInUserName);

    if(cashOutUserName.equals(cashInUserName)) return new ResponseEntity("Não é possível fazer transferência para si mesmo", HttpStatus.BAD_REQUEST);

    Optional<Account> accountUserCashOut = accountRepository.findById(userCashOut.getAccount().getId());
    accountUserCashOut.get().setBalance(accountUserCashOut.get().getBalance() - value);
    userRepository.save(userCashOut);

    Optional<Account> accountUserCashIn = accountRepository.findById(userCashIn.getAccount().getId());
    accountUserCashIn.get().setBalance(accountUserCashIn.get().getBalance() + value);
    userRepository.save(userCashIn);

    Transaction transaction = new Transaction();
    transaction.setDebitedAccountId(userCashOut.getAccount().getId());
    transaction.setCreditedAccountId(userCashIn.getAccount().getId());
    transaction.setValue(value);
    transaction.setCreatedAt(LocalDateTime.now());
    transactionRepository.save(transaction);

    return ResponseEntity.ok("Transação efetuada com sucesso");
  }


  public ResponseEntity<List<Transaction>> retrieveTransaction(long id, String cashInFilter, String cashOutFilter, String dateFilter){
//    System.out.println("imprimindo cashInFilter -> "+cashInFilter+"++++++++++++++++++++++++++");
//    System.out.println("imprimindo cashOutFilter -> "+cashOutFilter+"++++++++++++++++++++++++++");
//    System.out.println("imprimindo dateFilter -> "+dateFilter+"++++++++++++++++++++++++++");

    List<Transaction> transactions= transactionRepository.findAllBydebitedAccountId(id);
    List<Transaction> creditedTransactions= transactionRepository.findAllBycreditedAccountId(id);
    transactions.addAll(creditedTransactions);

    if(Boolean.parseBoolean(cashInFilter))
      transactions = transactions.stream().filter(transaction -> transaction.getCreditedAccountId() == id).toList();

    if(Boolean.parseBoolean(cashOutFilter))
      transactions = transactions.stream().filter(transaction -> transaction.getDebitedAccountId() == id).toList();

    if(dateFilter != null)
      transactions = transactions.stream().filter(transaction -> dateFilter.compareTo(transaction.getCreatedAt().toLocalDate().toString()) == 0).toList();


    return ResponseEntity.ok(transactions);
  }
}
