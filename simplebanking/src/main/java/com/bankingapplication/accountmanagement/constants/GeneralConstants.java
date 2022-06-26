package com.bankingapplication.accountmanagement.constants;

public class GeneralConstants {
    public static final String CREDIT_TRANSACTION = "CREDIT";
    public static final String DEBIT_TRANSACTION = "DEBIT";
    public static final String REMARKS_FOR_DEPOSIT = "Self deposit";
    public static final String MONEY_TRANSFTER_DEBIT_REMARKS = "TRANSFER_DEBIT";
    public static final String MONEY_TRANSFTER_CREDIT_REMARKS = "TRANSFER_DEBIT";
    public static final String ERR_INVALID_DETAILS_FOR_ACCOUNT_CREATE = "Customer name / Government issued ID is mandatory";
    public static final String ERR_ACC_ALREADY_EXIST = "Account Already exist with this id";
    public static final String ERR_INVALID_AMOUNT = "Invalid Amount";
    public static final String ERR_SAME_ACCOUNT = "Both From and To Account should not be same";
    public static final String ERR_INSUFFICIENT_BALANCE = "Insufficient balance in account";
    public static final String ERR_ACC_NOT_EXIST = "Account Number doesnot exist to fetch details";
    public static final String ERR_UNEXPECTED_ERROR = "Exception occured!! please retry";
    public static final String ERR_CONCURRENT_EXCEPTION = "Concurrency detected!! please retry";
}
