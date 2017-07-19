package com.ubs.interview.util;

import com.ubs.interview.dao.HardcodedProductEnum;
import com.ubs.interview.domain.Product;

public class TradeUtils {

    public static boolean isValidPriceMatch(String sellProductId, String buyProductId, int sellAmount, int buyAmount) {
        final Product sellProduct = HardcodedProductEnum.getProductById(sellProductId);
        final Product buyProduct = HardcodedProductEnum.getProductById(buyProductId);
        return (sellProduct.getSellPrice() * sellAmount == buyProduct.getBuyPrice() * buyAmount);
    }
}
