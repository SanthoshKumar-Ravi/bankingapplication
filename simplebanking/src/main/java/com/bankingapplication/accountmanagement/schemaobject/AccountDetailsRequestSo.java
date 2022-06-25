package com.bankingapplication.accountmanagement.schemaobject;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class AccountDetailsRequestSo {
    private String name;
    private String age;
    private String gender;
    private String governmentIssuedUniqueId;
    private String monthlyIncome;
    private String accountType;
}
