package com.bankingapplication.accountmanagement.controller;

import com.bankingapplication.accountmanagement.AccountManagementApplicationTests;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.service.AccountManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = AccountManagementApplicationTests.class)
@WebMvcTest(controllers = AccountManagementController.class)
public class AccountManagementControllerTest {
    @MockBean
    AccountManagementService accountManagementService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void givenRequestForAccountCreation_whenProcessing_thenReturnAccountCreationResponse() throws Exception {
        AccountDetailsResponseSo accountDetailsResponseSo = new AccountDetailsResponseSo();
        accountDetailsResponseSo.setAccountNo(1L);
        accountDetailsResponseSo.setName("ABC");
        accountDetailsResponseSo.setAge("23");
        accountDetailsResponseSo.setAccountType("Savings");
        accountDetailsResponseSo.setGender("male");
        accountDetailsResponseSo.setGovernmentIssuedUniqueId("1234");

        when(accountManagementService.createAccount(any())).thenReturn(accountDetailsResponseSo);

        this.mockMvc.perform(post("/api/v1/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDetailsResponseSo)))
                .andExpect(status().isOk()).andReturn();
    }
}
