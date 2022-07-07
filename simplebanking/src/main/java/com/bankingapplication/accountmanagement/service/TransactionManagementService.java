package com.bankingapplication.accountmanagement.service;

import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.DepositRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.TransactionSchemaObject;

import java.util.List;

public interface TransactionManagementService {
    AccountDetailsResponseSo deposit(DepositRequestSchemaObject depositRequestSchemaObject);
    AccountDetailsResponseSo transfer(MoneyTransferRequestSchemaObject moneyTransferRequestSchemaObject);
    List<TransactionSchemaObject> fetchTransaction(Long accountNumber);
}