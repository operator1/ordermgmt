package com.ubs.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		return "welcome";
	}

}