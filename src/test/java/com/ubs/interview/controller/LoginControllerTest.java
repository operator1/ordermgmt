package com.ubs.interview.controller;

import com.ubs.interview.view.UserCredentials;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class LoginControllerTest {

    @Test
    public void showLoginFormGoesToLoginView() throws Exception {

        // given
        final LoginController controller = new LoginController();

        // when
        final Map<String, Object> model = new HashMap<>();
        final String viewName = controller.showLoginForm(model);

        // then
        assertThat(model.get("userCredentials"), equalTo(new UserCredentials()));
        assertThat(viewName, equalTo("login"));
    }

    @Test
    public void loginHandlesError() throws Exception {

        // given
        final LoginController controller = new LoginController();
        final String error = "error";

        // when
        final ModelAndView modelAndView = controller.login(error, null);

        // then
        assertThat(modelAndView, notNullValue());
        assertThat(modelAndView.getViewName(), equalTo("login"));
        assertThat(modelAndView.getModel().get("message"), equalTo("Invalid username and password!"));
    }
}