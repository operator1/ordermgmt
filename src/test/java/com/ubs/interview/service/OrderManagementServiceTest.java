package com.ubs.interview.service;

import com.ubs.interview.dao.DataStore;
import com.ubs.interview.dao.DataStoreFactory;
import com.ubs.interview.domain.Portfolio;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class OrderManagementServiceTest {

    @Test
    public void registerUserDoesNotPersistYet() throws Exception {

        // given
        final DataStoreFactory dataStoreFactory = mock(DataStoreFactory.class);
        final OrderManagementService service = new OrderManagementService(dataStoreFactory);

        // when
        service.registerUser("user", "company", "email", "password");

        // then
        verifyNoMoreInteractions(dataStoreFactory);
    }

    @Test
    public void getAvailableProductsDelegatesToDataStore() throws Exception {

        // given
        final DataStoreFactory dataStoreFactory = mock(DataStoreFactory.class);
        final DataStore dataStore = mock(DataStore.class);
        final Portfolio availableProducts = mock(Portfolio.class);
        when(dataStore.getCurrentStockList()).thenReturn(availableProducts);
        when(dataStoreFactory.getSingletonInstance()).thenReturn(dataStore);
        final OrderManagementService service = new OrderManagementService(dataStoreFactory);

        // when
        final Portfolio portfolio = service.getAvailableProducts();

        // then
        assertThat(portfolio, equalTo(availableProducts));
    }

    @Test
    public void getPortfolioDelegatesToDataStore() throws Exception {

        // given
        final String user = "user";
        final DataStoreFactory dataStoreFactory = mock(DataStoreFactory.class);
        final DataStore dataStore = mock(DataStore.class);
        final Portfolio expectedPortfolio = mock(Portfolio.class);
        when(dataStore.getPortfolio(user)).thenReturn(expectedPortfolio);
        when(dataStoreFactory.getSingletonInstance()).thenReturn(dataStore);
        final OrderManagementService service = new OrderManagementService(dataStoreFactory);

        // when
        final Portfolio portfolio = service.getPortfolio(user);

        // then
        assertThat(portfolio, equalTo(expectedPortfolio));
    }
}