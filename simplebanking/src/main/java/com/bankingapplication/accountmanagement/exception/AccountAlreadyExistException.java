package com.bankingapplication.accountmanagement.exception;

public class AccountAlreadyExistException extends RuntimeException{
    private String message;
    public AccountAlreadyExistException() {}
    public AccountAlreadyExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
