package com.bankingapplication.accountmanagement.repository;

import com.bankingapplication.accountmanagement.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    int countByGovernmentIssuedUniqueIdAndStatus(String governmentIssuedUniqueId,boolean status);
    Accounts findByAccountNoAndStatus(Long accountNo, boolean status);
}
