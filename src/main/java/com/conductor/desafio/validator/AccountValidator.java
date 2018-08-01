package com.conductor.desafio.validator;

import com.conductor.desafio.model.Account;
import com.conductor.desafio.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Account account = (Account) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agency", "NotEmpty");
        if (account.getAgency().length() < 4 || account.getAgency().length() > 6) {
            errors.rejectValue("agency", "Size.accountForm.agency");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "NotEmpty");
        if (account.getNumber().length() < 4 || account.getNumber().length() > 6) {
            errors.rejectValue("number", "Size.accountForm.number");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "balance", "NotEmpty");
        if (account.getBalance() < 0) {
            errors.rejectValue("balance", "Balance.negative");
        }
    }
}
