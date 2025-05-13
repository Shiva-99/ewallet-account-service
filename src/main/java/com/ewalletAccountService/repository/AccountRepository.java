package com.ewalletAccountService.repository;

import com.ewalletAccountService.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
