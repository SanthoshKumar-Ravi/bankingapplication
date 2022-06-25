package com.bankingapplication.accountmanagement.schemaobject;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class DepositRequestSchemaObject {
    private String amountToBeDeposited;
    private Long accountNo;
}
