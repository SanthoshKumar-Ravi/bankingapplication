package com.bankingapplication.accountmanagement.schemaobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class MoneyTransferRequestSchemaObject {
    @ApiModelProperty(required = true)
    private Long fromAccount;
    @ApiModelProperty(required = true)
    private Long toAccount;
    @ApiModelProperty(required = true)
    private String transferAmount;
    private String notes;
}
