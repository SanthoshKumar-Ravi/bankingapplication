package com.bankingapplication.accountmanagement.schemaobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class AccountDetailsRequestSo {
    @ApiModelProperty(required = true)
    private String name;
    private String age;
    private String gender;
    @ApiModelProperty(required = true)
    private String governmentIssuedUniqueId;
    private String monthlyIncome;
    private String accountType;
}
