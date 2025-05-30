package com.ewalletAccountService.service.impl;

import com.ewalletAccountService.dto.AccountDto;
import com.ewalletAccountService.entity.Account;
import com.ewalletAccountService.mapper.AccountMapper;
import com.ewalletAccountService.repository.AccountRepository;
import com.ewalletAccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class AccountServiceImpl implements AccountService {

    public AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        return AccountMapper.mapToAccountDto(repository.save(AccountMapper.mapToAccount(accountDto)));
    }

    @Override
    public AccountDto getAccountById(Long id) {
        return AccountMapper.mapToAccountDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found!")));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return repository.findAll().stream().map(AccountMapper::mapToAccountDto).toList();
    }

    @Override
    public AccountDto depositMoney(Long id, Double amount) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        account.setBalance(account.getBalance() + amount);
        return AccountMapper.mapToAccountDto(repository.save(account));
    }

    @Override
    public AccountDto withdrawMoney(Long id, Double amount) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        account.setBalance(account.getBalance() - amount);
        return AccountMapper.mapToAccountDto(repository.save(account));
    }

    @Override
    public boolean deleteAccount(Long id) {
        if (!repository.existsById(id)) {
            return false;
        } else {
            repository.deleteById(id);
            return true;
        }
    }
}
