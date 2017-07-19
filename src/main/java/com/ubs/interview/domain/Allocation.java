package com.ubs.interview.domain;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Allocation {

    private final String productId;
    private final Map<String, Integer> userAllocations;

    public Allocation(String productId, Map<String, Integer> userAllocations) {
        this.productId = productId;
        this.userAllocations = ImmutableMap.copyOf(userAllocations);
    }

    public String getProductId() {
        return productId;
    }

    public Map<String, Integer> getUserAllocations() {
        return userAllocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Allocation that = (Allocation) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        return userAllocations != null ? userAllocations.equals(that.userAllocations) : that.userAllocations == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (userAllocations != null ? userAllocations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "productId='" + productId + '\'' +
                ", userAllocations=" + userAllocations +
                '}';
    }
}
