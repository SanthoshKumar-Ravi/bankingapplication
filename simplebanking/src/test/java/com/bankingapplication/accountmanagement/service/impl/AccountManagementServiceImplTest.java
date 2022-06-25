package com.bankingapplication.accountmanagement.service.impl;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.entity.Accounts;
import com.bankingapplication.accountmanagement.exception.AccountNotExistException;
import com.bankingapplication.accountmanagement.repository.AccountsRepository;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.validation.AccountsValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountManagementServiceImplTest {
    @InjectMocks
    AccountManagementServiceImpl accountManagementServiceImpl;
    @Mock
    AccountsValidator accountsValidator;
    @Mock
    AccountsRepository accountsRepository;

    @Test
    public void givenAccountNumber_whenFetchingTheData_thenThrowException(){
        try {
            // Mock
            Mockito.when(accountsRepository.findByAccountNoAndStatus(1L, true)).thenReturn(null);
            // when
            accountManagementServiceImpl.fetchAccount(1L);
        }catch(Exception ex){
            Assertions.assertThat(ex)
                    .isInstanceOf(AccountNotExistException.class)
                    .hasMessage(GeneralConstants.ERR_ACC_NOT_EXIST);
        }
    }

    @Test
    public void givenAccountNumber_whenFetchingTheData_thenReturnDetails(){
        // Given
        Accounts accounts = getAccounts();
        // Mock
        Mockito.when(accountsRepository.findByAccountNoAndStatus(1L, true)).thenReturn(accounts);
        // when
        AccountDetailsResponseSo accountDetailsResponseSo = accountManagementServiceImpl.fetchAccount(1L);
        // Then
        assertEquals(accountDetailsResponseSo.getAccountNo(), accounts.getAccountNo());
        assertEquals(accountDetailsResponseSo.getAccountType(), accounts.getAccountType());
        assertEquals(accountDetailsResponseSo.getAge(), accounts.getAge());
        assertEquals(accountDetailsResponseSo.getMonthlyIncome(), accounts.getMonthlyIncome());
        assertEquals(accountDetailsResponseSo.getGovernmentIssuedUniqueId(), accounts.getGovernmentIssuedUniqueId());
        assertEquals(accountDetailsResponseSo.getName(), accounts.getName());
    }

    @Test
    public void givenDetailsToCreateAccount_whenCreate_thenShouldSaveAndReturnTheData(){
        // Given
        AccountDetailsRequestSo accountDetailsRequestSo = AccountDetailsRequestSo.builder()
                .name("ABC")
                .accountType("Savings")
                .age("23")
                .governmentIssuedUniqueId("12345678")
                .monthlyIncome("2000")
                .build();
        Mockito.when(accountsRepository.save(Mockito.any())).thenReturn(getAccounts());
        // When
        accountManagementServiceImpl.createAccount(accountDetailsRequestSo);
        // Then
        ArgumentCaptor<Accounts> accountsArgumentCaptor = ArgumentCaptor.forClass(Accounts.class);
        Mockito.verify(accountsRepository).save(accountsArgumentCaptor.capture());
        Accounts accounts = accountsArgumentCaptor.getValue();
        assertEquals(accounts.getAccountType(), accountDetailsRequestSo.getAccountType());
        assertEquals(accounts.getBalance(), BigDecimal.ZERO);
        assertEquals(accounts.getAge(), accountDetailsRequestSo.getAge());
        assertEquals(accounts.getGovernmentIssuedUniqueId(), accountDetailsRequestSo.getGovernmentIssuedUniqueId());
        assertEquals(accounts.getMonthlyIncome(), accountDetailsRequestSo.getMonthlyIncome());
        assertEquals(accounts.getName(), accountDetailsRequestSo.getName());
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
                .build();
    }
}
