package com.bankingapplication.accountmanagement.controller;

import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsRequestSo;
import com.bankingapplication.accountmanagement.schemaobject.AccountDetailsResponseSo;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value="api/v1/account")
@RequestMapping("api/v1/account")
public interface AccountManagementApi {

    @ApiOperation(value = "API to create bank account", nickname = "Account Create API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "bank account created successfully"),
            @ApiResponse(code = 400, message = "bad request") })
    @PostMapping(value = "/create", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDetailsResponseSo> createAccount(@ApiParam(value = "Enter required details to create bank account" ,required=true )  @Valid @RequestBody AccountDetailsRequestSo accountDetailsRequestSo);


    @ApiOperation(value = "API to fetch account Details", nickname = "Account Details Fetch API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "bank account details fetched successfully"),
            @ApiResponse(code = 400, message = "bad request") })
    @GetMapping(value = "/fetchDetails/{accountNumber}", produces =  MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDetailsResponseSo> fetchAccount(@ApiParam(value = "Enter the account number" ,required=true )  @PathVariable("accountNumber") Long accountNumber);


}
