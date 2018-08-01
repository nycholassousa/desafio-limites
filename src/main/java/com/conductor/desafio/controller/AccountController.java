package com.conductor.desafio.controller;

import com.conductor.desafio.model.Account;
import com.conductor.desafio.model.User;
import com.conductor.desafio.repository.AccountRepository;
import com.conductor.desafio.repository.UserRepository;
import com.conductor.desafio.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/registeraccount", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("accountForm", new Account());

        return "registeraccount";
    }

    @RequestMapping(value = "/registeraccount", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("accountForm") Account accountForm, Model model, BindingResult bindingResult) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        accountValidator.validate(accountForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registeraccount";
        }

        model.addAttribute("number", accountForm.getNumber());
        model.addAttribute("agency", accountForm.getAgency());
        model.addAttribute("balance", accountForm.getBalance());
        accountForm.setOverdraft(100.0);
        accountForm.setUser(user);

        accountRepository.save(accountForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/credit/{id}", method = RequestMethod.GET)
    public String creditDebitValue(@PathVariable("id") Long id, Model model) {

        model.addAttribute("amount", "");
        model.addAttribute("id", accountRepository.findOne(id).getId());

        return "credit";
    }

    @RequestMapping(value = "/credit/{id}", method = RequestMethod.POST)
    public String creditDebitValue(@ModelAttribute("amount") double amount, @PathVariable("id") Long id) {
        Account currentAccount = accountRepository.findOne(id);
        currentAccount.setBalance(currentAccount.getBalance() + amount);

        accountRepository.save(currentAccount);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
    public String betweenAccounts(@PathVariable("id") Long id, Model model) {

        model.addAttribute("agency", "");
        model.addAttribute("number", "");
        model.addAttribute("amount", "");
        model.addAttribute("id", accountRepository.findOne(id).getId());

        return "transfer";
    }

    @RequestMapping(value = "/transfer/{id}", method = RequestMethod.POST)
    public String betweenAccounts(@ModelAttribute("agency") String agency, @ModelAttribute("number") String number, @ModelAttribute("amount") double amount, @PathVariable("id") Long id) {
        Account currentAccount = accountRepository.findOne(id);

        List<Account> accountsList = accountRepository.findAll();

        for (Account anAccountsList : accountsList) {
            if (anAccountsList.getAgency().equals(agency) && anAccountsList.getNumber().equals(number)) {

                currentAccount.setBalance(currentAccount.getBalance() - amount);
                anAccountsList.setBalance(anAccountsList.getBalance() + amount);

                accountRepository.save(currentAccount);
                accountRepository.save(anAccountsList);

                break;
            }
        }

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteAccount(@PathVariable("id") Long id){
        accountRepository.delete(id);

        return "redirect:/welcome";
    }
}
