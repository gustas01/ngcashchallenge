package com.gustavo.ngcashchallenge.repositories;

import com.gustavo.ngcashchallenge.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
