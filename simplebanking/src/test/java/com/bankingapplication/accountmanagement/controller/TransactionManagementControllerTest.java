package com.bankingapplication.accountmanagement.controller;

import com.bankingapplication.accountmanagement.AccountManagementApplicationTests;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.TransactionSchemaObject;
import com.bankingapplication.accountmanagement.service.TransactionManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = AccountManagementApplicationTests.class)
@WebMvcTest(controllers = TransactionManagementController.class)
public class TransactionManagementControllerTest {
    @MockBean
    TransactionManagementService transactionManagementService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void givenRequestForDeposit_whenProcessing_thenReturnTheUpdatedBalance() throws Exception {
        AccountDetailsResponseSo accountDetailsResponseSo = new AccountDetailsResponseSo();
        accountDetailsResponseSo.setAccountNo(1L);
        accountDetailsResponseSo.setBalance(new BigDecimal(10));

        when(transactionManagementService.deposit(any())).thenReturn(accountDetailsResponseSo);

        this.mockMvc.perform(post("/api/v1/transaction/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDetailsResponseSo)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void givenRequestForTransfer_whenProcessing_thenReturnTheUpdatedBalance() throws Exception {
        AccountDetailsResponseSo accountDetailsResponseSo = new AccountDetailsResponseSo();
        accountDetailsResponseSo.setAccountNo(1L);
        accountDetailsResponseSo.setBalance(new BigDecimal(10));

        when(transactionManagementService.transfer(any())).thenReturn(accountDetailsResponseSo);

        this.mockMvc.perform(post("/api/v1/transaction/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDetailsResponseSo)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void givenAccountNumber_whenFetchTheTransaction_thenReturnTheTopTenTransaction() throws Exception {
        TransactionSchemaObject transactionSchemaObject = TransactionSchemaObject.builder()
                .amount(new BigDecimal(10))
                .type("Credit")
                .remarks("Self Transfer")
                .build();
        when(transactionManagementService.fetchTransaction(1L)).thenReturn(Arrays.asList(transactionSchemaObject));

        this.mockMvc.perform(get("/api/v1/transaction/1")).andExpect(status().isOk()).andReturn();
    }
}
