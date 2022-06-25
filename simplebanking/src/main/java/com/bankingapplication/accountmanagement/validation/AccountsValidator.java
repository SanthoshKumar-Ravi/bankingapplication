package com.bankingapplication.accountmanagement.validation;

import com.bankingapplication.accountmanagement.constants.GeneralConstants;
import com.bankingapplication.accountmanagement.exception.AccountAlreadyExistException;
import com.bankingapplication.accountmanagement.exception.InvalidAccountDetailsException;
import com.bankingapplication.accountmanagement.repository.AccountsRepository;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountsValidator {
    private final AccountsRepository accountsRepository;


    public void doValidation(AccountDetailsRequestSo accountDetailsRequestSo){
        if(StringUtils.isEmpty(accountDetailsRequestSo.getGovernmentIssuedUniqueId()) ||
                StringUtils.isEmpty(accountDetailsRequestSo.getName())){
            throw new InvalidAccountDetailsException(GeneralConstants.ERR_INVALID_DETAILS_FOR_ACCOUNT_CREATE);
        }

        if(accountsRepository.countByGovernmentIssuedUniqueIdAndStatus(accountDetailsRequestSo.getGovernmentIssuedUniqueId(),true)>0){
            throw new AccountAlreadyExistException(GeneralConstants.ERR_ACC_ALREADY_EXIST);
        }
    }
}
