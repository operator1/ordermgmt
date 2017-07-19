package com.ubs.interview.controller;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class WelcomeControllerTest {

    @Test
    public void goesToWelcomeView() throws Exception {

        // given
        final WelcomeController controller = new WelcomeController();

        // when
        final String viewName = controller.home(new HashMap<>());

        // then
        assertThat(viewName, equalTo("welcome"));
    }
}