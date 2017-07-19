package com.ubs.interview.util;

import com.ubs.interview.dao.HardcodedProductEnum;
import org.junit.Test;

import static com.ubs.interview.dao.HardcodedProductEnum.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TradeUtilsTest {

    @Test
    public void returnsFalseWhenTradePricesDoNotMatch() throws Exception {
        doCanValidateTradePriceMatch(SHINY_NEW_PRODUCT, GOLD, 1, 1, false);
    }

    @Test
    public void returnsTrueWhenTradePricesMatch() throws Exception {
        doCanValidateTradePriceMatch(ABBA_RECORD, DARK_CHOCOLATE, 3, 3, true);
    }

    private void doCanValidateTradePriceMatch(
            HardcodedProductEnum sellProduct,
            HardcodedProductEnum buyProduct,
            int sellAmount,
            int buyAmount,
            boolean expectedValidity) throws Exception {

        // when
        final boolean isValidPriceMatch = TradeUtils.isValidPriceMatch(
                sellProduct.getProduct().getId(),
                buyProduct.getProduct().getId(),
                sellAmount,
                buyAmount);

        // then
        assertThat(isValidPriceMatch, is(expectedValidity));
    }
}