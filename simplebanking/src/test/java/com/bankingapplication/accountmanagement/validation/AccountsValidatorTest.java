package com.bankingapplication.accountmanagement.validation;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.exception.AccountAlreadyExistException;
import com.bankingapplication.accountmanagement.exception.InvalidAccountDetailsException;
import com.bankingapplication.accountmanagement.repository.AccountsRepository;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountsValidatorTest {
    @InjectMocks
    AccountsValidator accountsValidator;
    @Mock
    AccountsRepository accountsRepository;

    @Test
    public void givenEmptyUniqueId_whenValidating_thenThrowsInvalidAccountsException(){
        try {
            // given
            AccountDetailsRequestSo accountDetailsRequestSo = new AccountDetailsRequestSo();
            accountDetailsRequestSo.setName("ABC");
            accountDetailsRequestSo.setAge("23");
            accountDetailsRequestSo.setGender("male");
            accountDetailsRequestSo.setMonthlyIncome("3000");
            accountDetailsRequestSo.setAccountType("Savings");
            // when
            accountsValidator.doValidation(accountDetailsRequestSo);
        }catch(Exception e){
            // then
            Assertions.assertThat(e)
                    .isInstanceOf(InvalidAccountDetailsException.class)
                    .hasMessage(GeneralConstants.ERR_INVALID_DETAILS_FOR_ACCOUNT_CREATE);
        }
    }

    @Test
    public void givenEmptyName_whenValidating_thenThrowsInvalidAccountsException(){
        try {
            // given
            AccountDetailsRequestSo accountDetailsRequestSo = new AccountDetailsRequestSo();
            accountDetailsRequestSo.setGovernmentIssuedUniqueId("ABC");
            accountDetailsRequestSo.setAge("23");
            accountDetailsRequestSo.setGender("male");
            accountDetailsRequestSo.setMonthlyIncome("3000");
            accountDetailsRequestSo.setAccountType("Savings");
            // when
            accountsValidator.doValidation(accountDetailsRequestSo);
        }catch(Exception e){
            // then
            Assertions.assertThat(e)
                    .isInstanceOf(InvalidAccountDetailsException.class)
                    .hasMessage(GeneralConstants.ERR_INVALID_DETAILS_FOR_ACCOUNT_CREATE);
        }
    }

    @Test
    public void givenAccountDetailsExist_whenValidating_thenThrowsAccountAlreadyException(){
        try {
            // given
            AccountDetailsRequestSo accountDetailsRequestSo = new AccountDetailsRequestSo();
            accountDetailsRequestSo.setGovernmentIssuedUniqueId("ABC");
            accountDetailsRequestSo.setName("ABC");
            accountDetailsRequestSo.setAge("23");
            accountDetailsRequestSo.setGender("male");
            accountDetailsRequestSo.setMonthlyIncome("3000");
            accountDetailsRequestSo.setAccountType("Savings");
            Mockito.when(accountsRepository.countByGovernmentIssuedUniqueIdAndStatus(Mockito.any(), Mockito.anyBoolean())).thenReturn(1);
            // when
            accountsValidator.doValidation(accountDetailsRequestSo);
        }catch(Exception e){
            // then
            Assertions.assertThat(e)
                    .isInstanceOf(AccountAlreadyExistException.class)
                    .hasMessage(GeneralConstants.ERR_ACC_ALREADY_EXIST);
        }
    }
}
