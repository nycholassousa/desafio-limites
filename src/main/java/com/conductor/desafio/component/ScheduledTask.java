package com.conductor.desafio.component;

import com.conductor.desafio.model.Account;
import com.conductor.desafio.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ScheduledTask {
    @Autowired
    AccountRepository accountRepository;

    //Choose ONE Scheduled type.
    //@Scheduled(fixedRate = 30000) //30 seconds
    @Scheduled(cron = "0 0 12 * * *") //every day at 12pm
    public void scheduleDebit() {

        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            if (account.getBalance() < 0 && account.getOverdraft() > 0) {
                account.setBalance(account.getBalance() - (account.getBalance() * 0.1));
                accountRepository.save(account);
            }
        }
    }
}
