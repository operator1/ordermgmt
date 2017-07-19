package com.ubs.interview.controller;

import com.ubs.interview.view.UserRegistration;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegistration.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        final UserRegistration user = (UserRegistration) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty");

        if(StringUtils.hasText(user.getEmail()) && !EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "Pattern.userRegistration.email");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userRegistration.confirmPassword");
        }
    }
}
