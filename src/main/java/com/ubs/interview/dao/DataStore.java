package com.ubs.interview.dao;

import com.ubs.interview.domain.Allocation;
import com.ubs.interview.domain.Portfolio;
import com.ubs.interview.domain.Product;
import com.ubs.interview.domain.ProductStock;
import com.ubs.interview.util.TradeUtils;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class DataStore {

    private final Map<String, Allocation> allocations;
    private final Map<String, Lock> locks;

    DataStore(Map<String, Allocation> allocations) {

        this.allocations = new HashMap<>();
        this.allocations.putAll(allocations);

        this.locks = new HashMap<>(allocations.size());
        for (String product : allocations.keySet()) {
            locks.put(product, new ReentrantLock());
        }
    }

    public void executeClientTrade(
            String primaryTrader,
            String otherTrader,
            String sellProduct,
            String buyProduct,
            int sellAmount,
            int buyAmount) {

        validate(sellProduct, buyProduct, sellAmount, buyAmount);

        final Iterator<String> orderedProducts = orderProducts(sellProduct, buyProduct).iterator();

        final Lock lock1 = locks.get(orderedProducts.next());
        final Lock lock2 = locks.get(orderedProducts.next());

        // Ensure the sequence of these locks happens in the same order to avoid deadlock
        try {

            lock1.lock();
            lock2.lock();

            final Allocation sellProductAllocation = allocations.get(sellProduct);
            final Allocation buyProductAllocation = allocations.get(buyProduct);

            final Map<String, Integer> newSellProductUserAllocations = newHashMap();
            newSellProductUserAllocations.putAll(sellProductAllocation.getUserAllocations());

            final Map<String, Integer> newBuyProductUserAllocations = newHashMap();
            newBuyProductUserAllocations.putAll(buyProductAllocation.getUserAllocations());

            newSellProductUserAllocations.put(primaryTrader, getTranslateNullToZero(newSellProductUserAllocations, primaryTrader) - sellAmount);
            newSellProductUserAllocations.put(otherTrader, getTranslateNullToZero(newSellProductUserAllocations, otherTrader) + sellAmount);

            newBuyProductUserAllocations.put(primaryTrader, getTranslateNullToZero(newBuyProductUserAllocations, primaryTrader) + buyAmount);
            newBuyProductUserAllocations.put(otherTrader, getTranslateNullToZero(newBuyProductUserAllocations, otherTrader) - buyAmount);

            final Allocation newChocolateAllocation = new Allocation(sellProductAllocation.getProductId(), newSellProductUserAllocations);
            final Allocation newGoldAllocation = new Allocation(buyProductAllocation.getProductId(), newBuyProductUserAllocations);

            allocations.put(sellProduct, newChocolateAllocation);
            allocations.put(buyProduct, newGoldAllocation);

        } finally {

            lock2.unlock();
            lock1.unlock();
        }

    }

    private void validate(String sellProductId, String buyProductId, int sellAmount, int buyAmount) {
        if (!TradeUtils.isValidPriceMatch(sellProductId, buyProductId, sellAmount, buyAmount)) {
            throw new RuntimeException("Total buy and sell amounts do not match");
        }
    }

    private Integer getTranslateNullToZero(Map<String, Integer> map, String key) {
        final Integer value = map.get(key);
        if (value != null) {
            return value;
        }
        return 0;
    }

    public Portfolio getCurrentStockList() {
        return getPortfolio("ubs");
    }

    public Portfolio getPortfolio(String username) {

        final List<ProductStock> currentStock = newArrayList();

        for (Map.Entry<String, Allocation> entry : allocations.entrySet()) {

            final String productId = entry.getKey();
            final Allocation allocation = entry.getValue();

            final Map<String, Integer> userAllocations = allocation.getUserAllocations();
            if (userAllocations != null) {

                final Integer unitsAvailable = userAllocations.get(username);

                if (unitsAvailable != null && unitsAvailable > 0) {

                    final Product product = HardcodedProductEnum.getProductById(productId);
                    final ProductStock productStock = new ProductStock(product, unitsAvailable);
                    currentStock.add(productStock);
                }

            }
        }

        return new Portfolio(currentStock);
    }

    private Set<String> orderProducts(String... products) {
        final Set<String> treeSet = new TreeSet<>();
        Collections.addAll(treeSet, products);
        return treeSet;
    }

}
