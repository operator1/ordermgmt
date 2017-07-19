package com.ubs.interview.controller;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class AboutControllerTest {

    @Test
    public void goesToAboutView() throws Exception {

        // given
        final AboutController controller = new AboutController();

        // when
        final String viewName = controller.showAboutPage(new HashMap<>());

        // then
        assertThat(viewName, equalTo("about"));
    }
}