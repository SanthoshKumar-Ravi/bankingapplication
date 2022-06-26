package com.bankingapplication.accountmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@OptimisticLocking(type= OptimisticLockType.VERSION)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNo;
    private String name;
    private String age;
    private String gender;
    private String governmentIssuedUniqueId;
    private String monthlyIncome;
    private String accountType;
    private BigDecimal balance;
    private boolean status;
    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transactions> transactionsList;
    @Version
    private Long version;
}
