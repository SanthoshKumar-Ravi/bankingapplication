package com.bankingapplication.accountmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@OptimisticLocking(type= OptimisticLockType.VERSION)
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private BigDecimal amount;
    private String type;
    private String remarks;
    private String transactionDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Accounts accounts;
}
