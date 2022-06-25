package com.bankingapplication.accountmanagement.service.impl;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.entity.Accounts;
import com.bankingapplication.accountmanagement.entity.Transactions;
import com.bankingapplication.accountmanagement.exception.AccountNotExistException;
import com.bankingapplication.accountmanagement.repository.AccountsRepository;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.TransactionSchemaObject;
import com.bankingapplication.accountmanagement.service.AccountManagementService;
import com.bankingapplication.accountmanagement.validation.AccountsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountManagementService {
    private final AccountsValidator accountsValidator;
    private final AccountsRepository accountsRepository;


    @Override
    @Transactional
    public AccountDetailsResponseSo createAccount(AccountDetailsRequestSo accountDetailsRequestSo) {
        accountsValidator.doValidation(accountDetailsRequestSo);
        Accounts accounts = new Accounts();
        BeanUtils.copyProperties(accountDetailsRequestSo, accounts);
        accounts.setStatus(true);
        accounts.setBalance(BigDecimal.ZERO);
        Accounts accountsEntity = accountsRepository.save(accounts);
        AccountDetailsResponseSo accountDetailsResponseSo = new AccountDetailsResponseSo();
        BeanUtils.copyProperties(accountsEntity, accountDetailsResponseSo);
        return accountDetailsResponseSo;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDetailsResponseSo fetchAccount(Long accountNumber) {
        Accounts accounts = accountsRepository.findByAccountNoAndStatus(accountNumber,true);
        if(accounts!=null){
            AccountDetailsResponseSo accountDetailsResponseSo = new AccountDetailsResponseSo();
            BeanUtils.copyProperties(accounts, accountDetailsResponseSo);
            List<TransactionSchemaObject> transactionSchemaObjectList = new ArrayList<>();
            for(Transactions transactions : accounts.getTransactionsList()){
                TransactionSchemaObject transactionSchemaObject = new TransactionSchemaObject();
                BeanUtils.copyProperties(transactions, transactionSchemaObject);
                transactionSchemaObjectList.add(transactionSchemaObject);
            }
            accountDetailsResponseSo.setTransactionSchemaObjectList(transactionSchemaObjectList);
            return accountDetailsResponseSo;
        }else{
            throw new AccountNotExistException(GeneralConstants.ERR_ACC_NOT_EXIST);
        }
    }
}
