package com.ewalletAccountService.mapper;

import com.ewalletAccountService.dto.AccountDto;
import com.ewalletAccountService.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        return new Account(
          accountDto.getId(),
          accountDto.getAccountName(),
          accountDto.getUsername(),
          accountDto.getAccountType(),
          accountDto.getBalance()
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
          account.getId(),
          account.getAccountName(),
          account.getUsername(),
          account.getAccountType(),
          account.getBalance()
        );
    }
}