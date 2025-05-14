package com.ewalletAccountService.controller;

import com.ewalletAccountService.dto.AccountDto;
import com.ewalletAccountService.dto.UserDto;
import com.ewalletAccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountService accountService;

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUserName(@PathVariable Long id) {
        String url = "http://EWALLET-USER-SERVICE/api/users/" + id;
        UserDto user = restTemplate.getForObject(url, UserDto.class);
        return ResponseEntity.ok(Optional.ofNullable(user.getUsername()).orElseThrow(() -> new RuntimeException("No name found")));
    }

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> depositMoney(@PathVariable Long id, @RequestBody Map<String, Double> amount) {
        return ResponseEntity.ok(accountService.depositMoney(id, amount.get("balance")));
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable Long id, @RequestBody Map<String, Double> amount) {
        return ResponseEntity.ok(accountService.withdrawMoney(id, amount.get("withdraw")));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        if (accountService.deleteAccount(id)) {
            return ResponseEntity.ok("Account has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account is not found");
        }
    }
}
