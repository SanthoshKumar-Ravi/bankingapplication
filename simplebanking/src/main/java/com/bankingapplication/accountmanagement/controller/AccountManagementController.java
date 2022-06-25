package com.bankingapplication.accountmanagement.controller;

import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.service.AccountManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountManagementController implements AccountManagementApi{
    private final AccountManagementService accountManagementService;

    @Override
    public ResponseEntity<AccountDetailsResponseSo> createAccount(AccountDetailsRequestSo accountDetailsRequestSo) {
        log.info("Received request to create bank account : {}", accountDetailsRequestSo);
        AccountDetailsResponseSo accountDetailsResponseSo = accountManagementService.createAccount(accountDetailsRequestSo);
        return ResponseEntity.ok(accountDetailsResponseSo);
    }

    @Override
    public ResponseEntity<AccountDetailsResponseSo> fetchAccount(Long accountNumber) {
        log.info("Received request to fetch Account Details : {}", accountNumber);
        AccountDetailsResponseSo accountDetailsResponseSo = accountManagementService.fetchAccount(accountNumber);
        return ResponseEntity.ok(accountDetailsResponseSo);
    }
}
