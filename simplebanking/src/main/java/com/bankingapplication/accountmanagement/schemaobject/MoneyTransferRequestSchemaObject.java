package com.bankingapplication.accountmanagement.schemaobject;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class MoneyTransferRequestSchemaObject {
    private Long fromAccount;
    private Long toAccount;
    private String transferAmount;
    private String notes;
}
