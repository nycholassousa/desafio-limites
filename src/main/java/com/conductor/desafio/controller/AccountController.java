package com.conductor.desafio.controller;

import com.conductor.desafio.model.Account;
import com.conductor.desafio.model.User;
import com.conductor.desafio.repository.AccountRepository;
import com.conductor.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AccountController {

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
    public String registration(@Valid @ModelAttribute("accountForm") Account accountForm, BindingResult bindingResult, Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        if (bindingResult.hasErrors()) {
            return "error";
        }

        model.addAttribute("number", accountForm.getNumber());
        model.addAttribute("agency", accountForm.getAgency());
        model.addAttribute("balance", accountForm.getBalance());
        accountForm.setOverdraft(100.0);
        accountForm.setUser(user);

        accountRepository.save(accountForm);

        return "redirect:/welcome";
    }

}
