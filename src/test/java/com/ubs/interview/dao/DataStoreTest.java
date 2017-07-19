package com.ubs.interview.dao;

import com.google.common.collect.ImmutableMap;
import com.ubs.interview.domain.Allocation;
import com.ubs.interview.domain.Portfolio;
import com.ubs.interview.domain.ProductStock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ubs.interview.dao.HardcodedProductEnum.DARK_CHOCOLATE;
import static com.ubs.interview.dao.HardcodedProductEnum.GOLD;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DataStoreTest {

    @Test
    public void canExecuteTrade() throws Exception {

        // fred swaps 1 of his chocolates with 2 of bert's gold
        // => fred sells 1 chocolate to bert and fred buys 2 gold from bert
        // fred is called the primary trader because the trade happens from the perspective of fred with respect to buy / sell

        // given
        final Map<String, Allocation> allocations = allocations();
        final Portfolio expectedFredPortfolio = expectedFredPortfolio();
        final Portfolio expectedBertPortfolio = expectedBertPortfolio();

        final String primaryTrader = "fred";
        final String otherTrader = "bert";

        final String sellProduct = DARK_CHOCOLATE.getProduct().getId();
        final String buyProduct = HardcodedProductEnum.GOLD.getProduct().getId();

        final int sellAmount = 1;
        final int buyAmount = 2;
        final DataStore dataStore = new DataStore(allocations);

        // when
        dataStore.executeClientTrade(primaryTrader, otherTrader, sellProduct, buyProduct, sellAmount, buyAmount);
        final Portfolio actualFredPortfolio = dataStore.getPortfolio("fred");
        final Portfolio actualBertPortfolio = dataStore.getPortfolio("bert");

        // then
        assertThat(actualFredPortfolio, equalTo(expectedFredPortfolio));
        assertThat(actualBertPortfolio, equalTo(expectedBertPortfolio));

    }

    private Map<String, Allocation> allocations() {

        final Map<String, Allocation> allocations = new HashMap<>();

        final Map<String, Integer> chocolateUserAllocations = ImmutableMap.<String, Integer>builder()
                .put("fred", 10)
                .put("bert", 3)
                .build();
        allocations.put(DARK_CHOCOLATE.getProduct().getId(), new Allocation(DARK_CHOCOLATE.getProduct().getId(), chocolateUserAllocations));

        final Map<String, Integer> goldUserAllocations = ImmutableMap.<String, Integer>builder()
                .put("fred", 2)
                .put("bert", 7)
                .build();
        allocations.put(HardcodedProductEnum.GOLD.getProduct().getId(), new Allocation(HardcodedProductEnum.GOLD.getProduct().getId(), goldUserAllocations));

        return allocations;
    }
    
    private Portfolio expectedFredPortfolio() {
        final List<ProductStock> productStockList = new ArrayList<>();
        productStockList.add(new ProductStock(DARK_CHOCOLATE.getProduct(), 9));
        productStockList.add(new ProductStock(GOLD.getProduct(), 4));
        return new Portfolio(productStockList);
    }

    private Portfolio expectedBertPortfolio() {
        final List<ProductStock> productStockList = new ArrayList<>();
        productStockList.add(new ProductStock(DARK_CHOCOLATE.getProduct(), 4));
        productStockList.add(new ProductStock(GOLD.getProduct(), 5));
        return new Portfolio(productStockList);
    }
}