package com.ubs.interview.controller;

import com.ubs.interview.service.OrderManagementService;
import com.ubs.interview.view.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class RegisterController {

    private final OrderManagementService orderManagementService;
    private final UserRegistrationValidator userRegistrationValidator;

    @Autowired
    public RegisterController(OrderManagementService orderManagementService, UserRegistrationValidator userRegistrationValidator) {
        this.orderManagementService = orderManagementService;
        this.userRegistrationValidator = userRegistrationValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userRegistrationValidator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Map<String, Object> model) {
		final UserRegistration userRegistration = new UserRegistration();
		model.put("userRegistration", userRegistration);
		return "register";
	}

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String registerUser(
            Map<String, Object> model,
            @ModelAttribute("userRegistration") @Validated UserRegistration registration,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        orderManagementService.registerUser(
                registration.getUsername(),
                registration.getCompany(),
                registration.getEmail(),
                registration.getPassword());

        model.put("message", "Your registration was successful. Please log in with the details you provided.");

        return "welcome";
    }

}