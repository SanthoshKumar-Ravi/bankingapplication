package com.bankingapplication.accountmanagement.schemaobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class DepositRequestSchemaObject {
    @ApiModelProperty(required = true)
    private String amountToBeDeposited;
    @ApiModelProperty(required = true)
    private Long accountNo;
}
