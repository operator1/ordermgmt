package com.ubs.interview.controller;

import com.ubs.interview.service.OrderManagementService;
import com.ubs.interview.view.UserRegistration;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class RegisterControllerTest {

    @Test
    public void canShowRegistrationForm() throws Exception {

        // given
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final UserRegistrationValidator userRegistrationValidator = mock(UserRegistrationValidator.class);
        final RegisterController controller = new RegisterController(orderManagementService, userRegistrationValidator);
        final Map<String, Object> model = new HashMap<>();

        // when
        final String viewName = controller.showRegistrationForm(model);

        // then
        assertThat(viewName, equalTo("register"));
        assertThat(model.get("userRegistration"), equalTo(new UserRegistration()));
    }

    @Test
    public void canInitBinder() throws Exception {

        // given
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final UserRegistrationValidator userRegistrationValidator = mock(UserRegistrationValidator.class);
        final RegisterController controller = new RegisterController(orderManagementService, userRegistrationValidator);
        final WebDataBinder binder = mock(WebDataBinder.class);

        // when
        controller.initBinder(binder);

        // then
        verify(binder).setValidator(userRegistrationValidator);
    }

    @Test
    public void registerUserGoesBackToRegisterPageWhenThereAreErrors() throws Exception {

        // given
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final UserRegistrationValidator userRegistrationValidator = mock(UserRegistrationValidator.class);
        final RegisterController controller = new RegisterController(orderManagementService, userRegistrationValidator);
        final Map<String, Object> model = new HashMap<>();
        final BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        final UserRegistration userRegistration = new UserRegistration();

        // when
        final String viewName = controller.registerUser(model, userRegistration, bindingResult);

        // then
        assertThat(viewName, equalTo("register"));
        verify(bindingResult).hasErrors();
        verifyNoMoreInteractions(orderManagementService, userRegistrationValidator, bindingResult);
    }

    @Test
    public void registerUserPersistsRegistrationDetailsWhenThereAreNoErrors() throws Exception {

        // given
        final OrderManagementService orderManagementService = mock(OrderManagementService.class);
        final UserRegistrationValidator userRegistrationValidator = mock(UserRegistrationValidator.class);
        final RegisterController controller = new RegisterController(orderManagementService, userRegistrationValidator);
        final Map<String, Object> model = new HashMap<>();
        final BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        final UserRegistration userRegistration = new UserRegistration();
        userRegistration.setUsername("user");
        userRegistration.setPassword("password");
        userRegistration.setCompany("RBS");
        userRegistration.setEmail("abc@def.com");

        // when
        final String viewName = controller.registerUser(model, userRegistration, bindingResult);

        // then
        assertThat(viewName, equalTo("welcome"));
        verify(bindingResult).hasErrors();
        verify(orderManagementService).registerUser(userRegistration.getUsername(), userRegistration.getCompany(), userRegistration.getEmail(), userRegistration.getPassword());
        verifyNoMoreInteractions(userRegistrationValidator, bindingResult);
    }
}