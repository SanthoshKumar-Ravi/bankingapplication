package com.bankingapplication.accountmanagement.repository;

import com.bankingapplication.accountmanagement.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
