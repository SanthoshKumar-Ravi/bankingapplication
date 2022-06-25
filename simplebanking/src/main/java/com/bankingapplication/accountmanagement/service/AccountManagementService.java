package com.bankingapplication.accountmanagement.service;

import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import org.springframework.stereotype.Service;

@Service
public interface AccountManagementService {
    AccountDetailsResponseSo createAccount(AccountDetailsRequestSo accountDetailsRequestSo);
    AccountDetailsResponseSo fetchAccount(Long accountNumber);
}