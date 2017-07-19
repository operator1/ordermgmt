package com.ubs.interview.dao;

import com.ubs.interview.domain.Portfolio;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class DataStoreFactoryTest {

    @Test
    public void canGetUserPortfolioFromCannedData() throws Exception {

        // given
        final DataStore dataStore = new DataStoreFactory().getSingletonInstance();

        // when
        final Portfolio portfolio = dataStore.getPortfolio("user");

        // then
        assertThat(portfolio, notNullValue());
        assertThat(portfolio.getProductStockList(), notNullValue());
        assertThat(portfolio.getProductStockList().size(), equalTo(1));
        assertThat(portfolio.getProductStockList().get(0).getProduct(), equalTo(HardcodedProductEnum.ABBA_RECORD.getProduct()));
        assertThat(portfolio.getProductStockList().get(0).getUnitsAvailable(), equalTo(2));
    }
}