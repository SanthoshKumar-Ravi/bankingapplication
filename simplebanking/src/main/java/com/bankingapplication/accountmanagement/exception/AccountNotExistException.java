package com.bankingapplication.accountmanagement.exception;

public class AccountNotExistException extends RuntimeException{
    private String message;
    public AccountNotExistException() {}
    public AccountNotExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
