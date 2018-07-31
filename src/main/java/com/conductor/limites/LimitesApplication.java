package com.conductor.limites;

import com.conductor.limites.models.Account;
import com.conductor.limites.models.User;
import com.conductor.limites.repository.AccountRepository;
import com.conductor.limites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableJpaAuditing
public class LimitesApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(LimitesApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        clearData();
        saveData();
    }

    @Transactional
    private void clearData() {
        userRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Transactional
    private void saveData() {
        saveDataWithApproach();
    }


    /**
     * Save company first (not include product list). Then saving products which had attached a company for each.
     */
    private void saveDataWithApproach() {

        /*
         * Firstly persist users (not include accounts)
         */

        User user1 = new User("123456", "Usuario 1", "123");
        User user2 = new User("987654", "Usuario 2", "123");
        User user3 = new User("469873", "Usuario 3", "123");
        User user4 = new User("965145", "Usuario 4", "123");
        User user5 = new User("147852", "Usuario 5", "123");
        User user6 = new User("654789", "Usuario 6", "123");
        User user7 = new User("357159", "Usuario 7", "123");

        //save users
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);

        /*
         * Then store accounts with had persisted users.
         */
        Account account1 = new Account("1234", "123456", 12.0, user1);
        Account account2 = new Account("1237", "654987", 777.46, user1);
        Account account3 = new Account("8971", "917674", 65.20, user2);
        Account account4 = new Account("9870", "987321", 7.46, user3);
        Account account5 = new Account("8978", "654987", 77.46, user4);
        Account account6 = new Account("4532", "951753", 67.46, user5);
        Account account7 = new Account("9234", "987159", 6.46, user6);
        Account account8 = new Account("9157", "356987", 1.46, user7);
        Account account9 = new Account("6570", "458267", 0.46, user7);

        // save products
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);
        accountRepository.save(account5);
        accountRepository.save(account6);
        accountRepository.save(account7);
        accountRepository.save(account8);
        accountRepository.save(account9);
    }
}
