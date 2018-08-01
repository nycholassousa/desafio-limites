package com.conductor.desafio.controller;

import com.conductor.desafio.model.Account;
import com.conductor.desafio.model.User;
import com.conductor.desafio.repository.AccountRepository;
import com.conductor.desafio.repository.UserRepository;
import com.conductor.desafio.service.SecurityService;
import com.conductor.desafio.service.UserService;
import com.conductor.desafio.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        List<Account> accountsList = accountRepository.findAll();
        List<Account> aux = new ArrayList<>();

        for (int i = 0; i < accountsList.size(); i++) {
            if (accountsList.get(i).getUser().getId() == user.getId()) {
                aux.add(accountsList.get(i));
            }
        }

        model.addAttribute("accounts", aux);

        return "welcome";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String betweenAccounts(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        model.addAttribute("username", user.getUsername());

        return "edituser";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String betweenAccountsPost(@ModelAttribute("username") String username) {

        User currentUser = userRepository.findByUsername(username);

        Collection<SimpleGrantedAuthority> nowAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, currentUser.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/welcome";
    }
}
