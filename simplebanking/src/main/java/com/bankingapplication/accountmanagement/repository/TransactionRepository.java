package com.bankingapplication.accountmanagement.repository;

import com.bankingapplication.accountmanagement.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    public List<Transactions> findFirst10ByAccounts_AccountNoOrderByTransactionIdDesc(Long accountId);
}
