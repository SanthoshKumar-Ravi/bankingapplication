package com.bankingapplication.accountmanagement.validation;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.entity.Accounts;
import com.bankingapplication.accountmanagement.exception.GenericException;
import com.bankingapplication.accountmanagement.exception.InsufficientBalanceException;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class TransactionValidator {
    public void doValidationForDeposit(String amount){
        try{
            if(new BigDecimal(amount).compareTo(BigDecimal.ZERO)  < 1 ){
                throw new InsufficientBalanceException(GeneralConstants.ERR_INVALID_AMOUNT);
            }
        }catch(Exception e) {
            throw new InsufficientBalanceException(GeneralConstants.ERR_INVALID_AMOUNT);
        }
    }

    public void doValidationForTransfer(MoneyTransferRequestSchemaObject moneyTransferRequestSchemaObject) {
        doValidationForDeposit(moneyTransferRequestSchemaObject.getTransferAmount());
        if(moneyTransferRequestSchemaObject.getFromAccount() == moneyTransferRequestSchemaObject.getToAccount()){
            throw new GenericException(GeneralConstants.ERR_SAME_ACCOUNT);
        }

    }

    public void doValidationForBalance(MoneyTransferRequestSchemaObject moneyTransferRequestSchemaObject, Accounts fromAccounts) {
        if(fromAccounts.getBalance().compareTo(new BigDecimal(moneyTransferRequestSchemaObject.getTransferAmount())) < 1){
            throw new InsufficientBalanceException(GeneralConstants.ERR_INSUFFICIENT_BALANCE);
        }
    }
}
