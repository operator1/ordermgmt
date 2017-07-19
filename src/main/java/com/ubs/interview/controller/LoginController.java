package com.ubs.interview.controller;

import com.ubs.interview.view.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Map<String, Object> model) {
		final UserCredentials userCredentials = new UserCredentials();
		model.put("userCredentials", userCredentials);
		return "login";
	}

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        final ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("message", "Invalid username and password!");
            model.setViewName("login");
        }

        if (logout != null) {
            model.addObject("message", "You've been logged out successfully.");
            model.setViewName("welcome");
        }


        return model;
    }

}