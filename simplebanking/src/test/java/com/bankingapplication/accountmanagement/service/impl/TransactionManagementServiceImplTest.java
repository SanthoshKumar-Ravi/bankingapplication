package com.bankingapplication.accountmanagement.service.impl;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.entity.Accounts;
import com.bankingapplication.accountmanagement.entity.Transactions;
import com.bankingapplication.accountmanagement.exception.AccountNotExistException;
import com.bankingapplication.accountmanagement.repository.AccountsRepository;
import com.bankingapplication.accountmanagement.repository.TransactionRepository;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.DepositRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.TransactionSchemaObject;
import com.bankingapplication.accountmanagement.validation.TransactionValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.bankingapplication.accountmanagement.constants.GeneralConstants.CREDIT_TRANSACTION;
import static com.bankingapplication.accountmanagement.constants.GeneralConstants.REMARKS_FOR_DEPOSIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionManagementServiceImplTest {
    @InjectMocks
    TransactionManagementServiceImpl transactionManagementServiceImpl;
    @Mock
    AccountsRepository accountsRepository;
    @Mock
    TransactionValidator transactionValidator;
    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void givenInvalidAccountNoForDeposit_whenDeposit_thenThrowException(){
        try{
            // Mock
            Mockito.when(accountsRepository.findByAccountNoAndStatus(1L,true)).thenReturn(null);
            // when
            transactionManagementServiceImpl.deposit(getDepositRequest());
        }catch(Exception e){
            // Then
            Assertions.assertThat(e)
                    .isInstanceOf(AccountNotExistException.class)
                    .hasMessage(GeneralConstants.ERR_ACC_NOT_EXIST);
        }
    }

    @Test
    public void givenValidAccountNoForDeposit_whenDeposit_thenShouldIncreaseTheBalance(){
        // Mock
        Accounts accounts = getAccounts();
        Mockito.when(accountsRepository.findByAccountNoAndStatus(1L,true)).thenReturn(accounts);
        // when
        DepositRequestSchemaObject depositRequestSchemaObject = getDepositRequest();
        AccountDetailsResponseSo accountDetailsResponseSo = transactionManagementServiceImpl.deposit(depositRequestSchemaObject);
        // Then
        ArgumentCaptor<Transactions> transactionsArgumentCaptor = ArgumentCaptor.forClass(Transactions.class);
        Mockito.verify(transactionRepository).save(transactionsArgumentCaptor.capture());
        Transactions transactions = transactionsArgumentCaptor.getValue();
        assertEquals(accountDetailsResponseSo.getAccountNo(), accounts.getAccountNo());
        assertEquals(accountDetailsResponseSo.getBalance(), new BigDecimal(13));
        assertEquals(transactions.getAmount(), new BigDecimal(depositRequestSchemaObject.getAmountToBeDeposited()));
        assertEquals(transactions.getRemarks(),REMARKS_FOR_DEPOSIT);
        assertEquals(transactions.getType(),CREDIT_TRANSACTION);
    }

    @Test
    public void givenInvalidAccountNoForTransfer_whenTransfer_thenThrowException(){
        try{
            // Mock
            Mockito.when(accountsRepository.findByAccountNoAndStatus(1L,true)).thenReturn(null);
            // when
            transactionManagementServiceImpl.transfer(getMoneyTransferRequestSchemaObject());
        }catch(Exception e){
            // Then
            Assertions.assertThat(e)
                    .isInstanceOf(AccountNotExistException.class)
                    .hasMessage(GeneralConstants.ERR_ACC_NOT_EXIST);
        }
    }

    @Test
    public void givenValidAccountNoForTransfer_whenTransfer_thenShouldProcessTheTransaction(){
        // Mock
        Accounts fromAccount = getAccounts();
        fromAccount.setAccountNo(1L);
        fromAccount.setBalance(new BigDecimal("20"));
        Mockito.when(accountsRepository.findByAccountNoAndStatus(1L,true)).thenReturn(fromAccount);
        Accounts toAccount = getAccounts();
        toAccount.setAccountNo(2L);
        Mockito.when(accountsRepository.findByAccountNoAndStatus(2L,true)).thenReturn(toAccount);
        // when
        AccountDetailsResponseSo accountDetailsResponseSo = transactionManagementServiceImpl.transfer(getMoneyTransferRequestSchemaObject());
        // Then
        Mockito.verify(transactionRepository, Mockito.times(2)).save(Mockito.any());
        assertEquals(accountDetailsResponseSo.getAccountNo(), fromAccount.getAccountNo());
        assertEquals(accountDetailsResponseSo.getBalance(), new BigDecimal("10"));
    }

    @Test
    public void givenAccountNumber_whenFetchingTransactions_thenShouldReturnTheTopTenTransactions(){
        // Mock
        Transactions transactions = getTransactions();
        Mockito.when(transactionRepository.findTopTenTransactions(1L)).thenReturn(Arrays.asList(transactions));
        // When
        List<TransactionSchemaObject> transactionSchemaObjectList = transactionManagementServiceImpl.fetchTransaction(1L);
        // Then
        assertEquals(transactionSchemaObjectList.size(),1);
    }

    private Transactions getTransactions(){
        return Transactions.builder()
                .amount(new BigDecimal(10))
                .transactionId(1L)
                .remarks("Emergency Transfer")
                .type("Credit")
                .transactionDate(new Date().toString())
                .build();
    }

    private Accounts getAccounts(){
        return Accounts.builder()
                .accountNo(1L)
                .accountType("Savings")
                .age("23")
                .monthlyIncome("2000")
                .governmentIssuedUniqueId("N34567")
                .name("ABC")
                .status(true)
                .balance(new BigDecimal(1))
                .build();
    }

    private DepositRequestSchemaObject getDepositRequest(){
        return DepositRequestSchemaObject.builder().amountToBeDeposited("12").accountNo(1L).build();
    }

    private MoneyTransferRequestSchemaObject getMoneyTransferRequestSchemaObject(){
        return MoneyTransferRequestSchemaObject.builder().fromAccount(1L).toAccount(2L).transferAmount("10").notes("Transfer").build();
    }
}
