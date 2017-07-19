package com.ubs.interview.domain;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Portfolio {

    private final List<ProductStock> productStockList;

    public Portfolio(List<ProductStock> productStockList) {
        this.productStockList = ImmutableList.copyOf(productStockList);
    }

    public List<ProductStock> getProductStockList() {
        return productStockList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Portfolio portfolio = (Portfolio) o;

        return productStockList != null ? productStockList.equals(portfolio.productStockList) : portfolio.productStockList == null;
    }

    @Override
    public int hashCode() {
        return productStockList != null ? productStockList.hashCode() : 0;
    }
}
