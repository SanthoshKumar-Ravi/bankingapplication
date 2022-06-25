package com.bankingapplication.accountmanagement.validation;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.entity.Accounts;
import com.bankingapplication.accountmanagement.exception.GenericException;
import com.bankingapplication.accountmanagement.exception.InsufficientBalanceException;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class TransactionValidatorTest {
    @InjectMocks
    TransactionValidator transactionValidator;

    @Test
    public void givenNullAmount_whenValidate_thenShouldThrowInsufficientException(){
        try{
            transactionValidator.doValidationForDeposit(null);
        }catch(Exception ex){
            Assertions.assertThat(ex)
                    .isInstanceOf(InsufficientBalanceException.class)
                    .hasMessage(GeneralConstants.ERR_INVALID_AMOUNT);
        }
    }

    @Test
    public void givenZeroAmount_whenValidate_thenShouldThrowInsufficientException(){
        try{
            transactionValidator.doValidationForDeposit("0");
        }catch(Exception ex){
            Assertions.assertThat(ex)
                    .isInstanceOf(InsufficientBalanceException.class)
                    .hasMessage(GeneralConstants.ERR_INVALID_AMOUNT);
        }
    }

    @Test
    public void givenSameFromAndToAccount_whenValidate_thenShouldThrowGenericException(){
        try{
            transactionValidator.doValidationForTransfer(MoneyTransferRequestSchemaObject.builder()
                    .fromAccount(1L)
                    .toAccount(1L)
                    .transferAmount("10")
                    .build());
        }catch(Exception ex){
            Assertions.assertThat(ex)
                    .isInstanceOf(GenericException.class)
                    .hasMessage(GeneralConstants.ERR_SAME_ACCOUNT);
        }
    }

    @Test
    public void givenZeroBalanceInFromAccount_whenValidate_thenShouldThrowInsufficientException(){
        try{
            transactionValidator.doValidationForBalance(MoneyTransferRequestSchemaObject.builder()
                    .fromAccount(1L)
                    .toAccount(1L)
                    .transferAmount("10")
                    .build(), Accounts.builder().balance(new BigDecimal(8)).build());
        }catch(Exception ex){
            Assertions.assertThat(ex)
                    .isInstanceOf(InsufficientBalanceException.class)
                    .hasMessage(GeneralConstants.ERR_INSUFFICIENT_BALANCE);
        }
    }
}
