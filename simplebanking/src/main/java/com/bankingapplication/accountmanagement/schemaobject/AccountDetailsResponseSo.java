package com.bankingapplication.accountmanagement.schemaobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetailsResponseSo {
    private String name;
    private String age;
    private String gender;
    private String governmentIssuedUniqueId;
    private String monthlyIncome;
    private String accountType;
    private Long accountNo;
    private BigDecimal balance;
    private List<TransactionSchemaObject> transactionSchemaObjectList;
}
