package com.bankingapplication.accountmanagement.schemaobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionSchemaObject {
    private BigDecimal amount;
    private String type;
    private String remarks;
    private String transactionDate;
}
