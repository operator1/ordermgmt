package com.ubs.interview.dao;

import com.ubs.interview.domain.Product;

/**
 * A hardcoded list of products to help stub out the need for a db during development.
 */
public enum HardcodedProductEnum {

    SHINY_NEW_PRODUCT(new Product("product1", "Shiny new toy", 6.5, 5.8)),
    DARK_CHOCOLATE(new Product("product2", "Dark chocolate", 7, 6.5)),
    GOLD(new Product("product3", "Gold", 49.3, 42.4)),
    ABBA_RECORD(new Product("product4", "Abba record", 8, 7));

    private final Product product;

    HardcodedProductEnum(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public static Product getProductById(String id) {
        for (HardcodedProductEnum hardcodedProductEnum : values()) {
            if (hardcodedProductEnum.getProduct().getId().equals(id)) {
                return hardcodedProductEnum.getProduct();
            }
        }
        throw new RuntimeException("There is no product with id: " + id);
    }
}
