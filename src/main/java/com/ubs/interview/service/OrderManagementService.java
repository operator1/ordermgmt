package com.ubs.interview.service;

import com.ubs.interview.dao.DataStoreFactory;
import com.ubs.interview.domain.Portfolio;
import com.ubs.interview.domain.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderManagementService {

    private final DataStoreFactory dataStoreFactory;

    @Autowired
    public OrderManagementService(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    public void registerUser(String username, String company, String email, String password) {
        // Persist here
    }

    public Portfolio getAvailableProducts() {
        return dataStoreFactory.getSingletonInstance().getCurrentStockList();
    }

    public Portfolio getPortfolio(String username) {
        return dataStoreFactory.getSingletonInstance().getPortfolio(username);
    }

    public ProductStock getProductStock(String username, String productId) {

        final Portfolio portfolio = dataStoreFactory.getSingletonInstance().getPortfolio(username);

        for (ProductStock productStock : portfolio.getProductStockList()) {
            if (productStock.getProduct().getId().equals(productId)) {
                return productStock;
            }
        }

        return null;
    }

    public void executeClientTrade(
            String primaryTrader,
            String otherTrader,
            String sellProduct,
            String buyProduct,
            int sellAmount,
            int buyAmount) {

        dataStoreFactory.getSingletonInstance().executeClientTrade(
                primaryTrader,
                otherTrader,
                sellProduct,
                buyProduct,
                sellAmount,
                buyAmount);
    }
}
