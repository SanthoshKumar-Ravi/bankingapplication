package com.bankingapplication.accountmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = InvalidAccountDetailsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(InvalidAccountDetailsException ex)
    {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = AccountAlreadyExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public @ResponseBody ErrorResponse handleException(AccountAlreadyExistException ex)
    {
        return new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public @ResponseBody ErrorResponse handleException(InsufficientBalanceException ex)
    {
        return new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = AccountNotExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public @ResponseBody ErrorResponse handleException(AccountNotExistException ex)
    {
        return new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = GenericException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public @ResponseBody ErrorResponse handleException(GenericException ex)
    {
        return new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }
}
