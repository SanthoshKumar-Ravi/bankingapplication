package com.bankingapplication.accountmanagement.controller;

import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.DepositRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.TransactionSchemaObject;
import com.bankingapplication.accountmanagement.service.TransactionManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionManagementController implements TransactionManagementApi {
    private final TransactionManagementService transactionManagementService;

    @Override
    public ResponseEntity<AccountDetailsResponseSo> deposit(DepositRequestSchemaObject depositRequestSchemaObject) {
        log.info("Received request to deposit amount : {}", depositRequestSchemaObject);
        AccountDetailsResponseSo accountDetailsResponseSo = transactionManagementService.deposit(depositRequestSchemaObject);
        return ResponseEntity.ok(accountDetailsResponseSo);
    }

    @Override
    public ResponseEntity<AccountDetailsResponseSo> transfer(MoneyTransferRequestSchemaObject moneyTransferRequestSchemaObject) {
        log.info("Received request for money transfer : {}", moneyTransferRequestSchemaObject);
        AccountDetailsResponseSo accountDetailsResponseSo = transactionManagementService.transfer(moneyTransferRequestSchemaObject);
        return ResponseEntity.ok(accountDetailsResponseSo);
    }

    @Override
    public ResponseEntity<List<TransactionSchemaObject>> fetchTransaction(Long accountNumber) {
        List<TransactionSchemaObject> transactionSchemaObjectList = transactionManagementService.fetchTransaction(accountNumber);
        return ResponseEntity.ok(transactionSchemaObjectList);
    }
}
