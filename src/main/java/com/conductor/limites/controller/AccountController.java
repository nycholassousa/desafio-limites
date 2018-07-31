package com.conductor.limites.controller;

import com.conductor.limites.exceptions.ResourceNotFoundException;
import com.conductor.limites.models.Account;
import com.conductor.limites.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    //Get All Accounts
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    //Create a new Account
    @PostMapping("/accounts/new")
    public Account createAccount(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

    //Get a single Account
    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable(value = "id") long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
    }

    //Update an Account -- Set Balance
    @PutMapping("/accounts/{id}")
    public Account updateAccount(@PathVariable(value = "id") long accountId, @Valid @RequestBody Account details) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        account.setBalance(details.getBalance());
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }

    //Delete an Account
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(value = "id") long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        accountRepository.delete(account);

        return ResponseEntity.ok().build();
    }
}
