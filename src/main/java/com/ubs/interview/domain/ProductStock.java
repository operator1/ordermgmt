package com.ubs.interview.domain;

public class ProductStock {

    private final Product product;
    private final int unitsAvailable;

    public ProductStock(Product product, int unitsAvailable) {
        this.product = product;
        this.unitsAvailable = unitsAvailable;
    }

    public Product getProduct() {
        return product;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ProductStock that = (ProductStock) o;

        if (unitsAvailable != that.unitsAvailable) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + unitsAvailable;
        return result;
    }

    @Override
    public String toString() {
        return "ProductStock{" +
                "product=" + product +
                ", unitsAvailable=" + unitsAvailable +
                '}';
    }
}
