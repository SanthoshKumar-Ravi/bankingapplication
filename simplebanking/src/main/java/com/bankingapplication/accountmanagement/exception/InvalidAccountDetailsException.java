package com.bankingapplication.accountmanagement.exception;

import lombok.Data;

@Data
public class InvalidAccountDetailsException extends  RuntimeException
{
    private String message;
    public InvalidAccountDetailsException() {}
    public InvalidAccountDetailsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}