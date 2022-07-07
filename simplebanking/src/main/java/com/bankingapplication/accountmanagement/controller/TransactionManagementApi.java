package com.bankingapplication.accountmanagement.controller;

import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import com.bankingapplication.accountmanagement.schemaobject.DepositRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.MoneyTransferRequestSchemaObject;
import com.bankingapplication.accountmanagement.schemaobject.TransactionSchemaObject;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value="api/v1/transaction")
@RequestMapping("api/v1/transaction")
public interface TransactionManagementApi {
    @ApiOperation(value = "API to deposit money in the account", nickname = "Account Deposit API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Money Deposited Successfully in the bank Account"),
            @ApiResponse(code = 400, message = "bad request") })
    @PostMapping(value = "/deposit", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDetailsResponseSo> deposit(@ApiParam(value = "Enter required details to deposit money" ,required=true )  @Valid @RequestBody DepositRequestSchemaObject depositRequestSchemaObject);

    @ApiOperation(value = "API to transfer money between accounts", nickname = "Amount Transfer API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Money Deposited Successfully in the bank Account"),
            @ApiResponse(code = 400, message = "bad request") })
    @PostMapping(value = "/transfer", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDetailsResponseSo> transfer(@ApiParam(value = "Enter required details to transfer the money" ,required=true )  @Valid @RequestBody MoneyTransferRequestSchemaObject moneyTransferRequestSchemaObject);

    @GetMapping(value = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TransactionSchemaObject>> fetchTransaction(@PathVariable("accountNumber") Long accountNumber);
}
