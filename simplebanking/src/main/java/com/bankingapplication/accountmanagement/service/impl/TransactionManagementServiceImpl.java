package com.bankingapplication.accountmanagement.service.impl;

import com.bankingapplication.accountmanagement.entity.Accounts;
import com.bankingapplication.accountmanagement.entity.Transactions;
import com.bankingapplication.accountmanagement.exception.AccountNotExistException;
import com.bankingapplication.accountmanagement.repository.AccountsRepository;
import com.bankingapplication.accountmanagement.repository.TransactionRepository;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.DepositRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import com.bankingapplication.accountmanagement.service.TransactionManagementService;
import com.bankingapplication.accountmanagement.validation.TransactionValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bankingapplication.accountmanagement.constants.GeneralConstants.*;

@Service
@RequiredArgsConstructor
public class TransactionManagementServiceImpl implements TransactionManagementService {
    private final AccountsRepository accountsRepository;
    private final TransactionValidator transactionValidator;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public synchronized AccountDetailsResponseSo deposit(DepositRequestSchemaObject depositRequestSchemaObject) {
        transactionValidator.doValidationForDeposit(depositRequestSchemaObject.getAmountToBeDeposited());
        Accounts accounts = accountsRepository.findByAccountNoAndStatus(depositRequestSchemaObject.getAccountNo(), Boolean.TRUE);
        if(accounts!=null){
            BigDecimal amount = new BigDecimal(depositRequestSchemaObject.getAmountToBeDeposited());
            accounts.setBalance(accounts.getBalance().add(amount));
            createTransaction(amount, REMARKS_FOR_DEPOSIT, CREDIT_TRANSACTION,LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    ,accounts);
            return AccountDetailsResponseSo.builder()
                    .accountNo(accounts.getAccountNo())
                    .balance(accounts.getBalance())
                    .build();
        }else{
            throw new AccountNotExistException(ERR_ACC_NOT_EXIST);
        }
    }

    @Override
    @Transactional
    public synchronized AccountDetailsResponseSo transfer(MoneyTransferRequestSchemaObject moneyTransferRequestSchemaObject) {
        transactionValidator.doValidationForTransfer(moneyTransferRequestSchemaObject);
        Accounts fromAccounts = accountsRepository.findByAccountNoAndStatus(moneyTransferRequestSchemaObject.getFromAccount(), Boolean.TRUE);
        Accounts toAccounts = accountsRepository.findByAccountNoAndStatus(moneyTransferRequestSchemaObject.getToAccount(), Boolean.TRUE);
        if(fromAccounts!=null && toAccounts!=null){
            transactionValidator.doValidationForBalance(moneyTransferRequestSchemaObject, fromAccounts);
            BigDecimal amount = new BigDecimal(moneyTransferRequestSchemaObject.getTransferAmount());
            fromAccounts.setBalance(fromAccounts.getBalance().subtract(amount));
            createTransaction(amount,
                    StringUtils.isNotBlank(moneyTransferRequestSchemaObject.getNotes())? moneyTransferRequestSchemaObject.getNotes() : MONEY_TRANSFTER_DEBIT_REMARKS,
                    DEBIT_TRANSACTION,
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    fromAccounts);
            toAccounts.setBalance(toAccounts.getBalance().add(amount));
            createTransaction(amount,
                    StringUtils.isNotBlank(moneyTransferRequestSchemaObject.getNotes())? moneyTransferRequestSchemaObject.getNotes() : MONEY_TRANSFTER_CREDIT_REMARKS,
                    CREDIT_TRANSACTION,
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    toAccounts);
            return AccountDetailsResponseSo.builder()
                    .accountNo(moneyTransferRequestSchemaObject.getFromAccount())
                    .balance(fromAccounts.getBalance())
                    .build();
        }else{
            throw new AccountNotExistException(ERR_ACC_NOT_EXIST);
        }

    }

    private void createTransaction(BigDecimal amount, String remarks, String debitTransaction, String dateFormat, Accounts accounts) {
        Transactions transactions = Transactions.builder()
                .amount(amount)
                .remarks(remarks)
                .type(debitTransaction)
                .transactionDate(dateFormat)
                .accounts(accounts)
                .build();
        transactionRepository.save(transactions);
    }
}
