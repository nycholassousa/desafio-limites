package com.conductor.limites.component;

import com.conductor.limites.models.Account;
import com.conductor.limites.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ScheduledTasks {
    @Autowired
    AccountRepository accountRepository;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    //Choose ONE Scheduled type.
    //@Scheduled(fixedRate = 30000) //30 seconds
    @Scheduled(cron = "0 0 12 * * *") //every day at 12pm
    public void scheduleDebit() {

        List<Account> accounts = accountRepository.findAll();

        for (Account account : accounts) {
            account.setBalance(account.getBalance() - (account.getBalance() * 0.1));
            accountRepository.save(account);
        }
    }
}
