package com.bankingapplication.accountmanagement.exception;

public class InsufficientBalanceException extends RuntimeException {
    private String message;
    public InsufficientBalanceException() {}
    public InsufficientBalanceException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
