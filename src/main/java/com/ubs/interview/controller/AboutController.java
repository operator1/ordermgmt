package com.ubs.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class AboutController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
	public String showAboutPage(Map<String, Object> model) {
		return "about";
	}
}