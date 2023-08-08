package com.gustavo.ngcashchallenge.repositories;

import com.gustavo.ngcashchallenge.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllBydebitedAccountId(long debitedAccountId);
  List<Transaction> findAllBycreditedAccountId(long creditedAccountId);
}
