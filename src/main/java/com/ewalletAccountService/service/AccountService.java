package com.ewalletAccountService.service;

import com.ewalletAccountService.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto depositMoney(Long id, Double amount);
    AccountDto withdrawMoney(Long id, Double amount);
    boolean deleteAccount(Long id);
}
