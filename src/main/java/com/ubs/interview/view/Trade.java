package com.ubs.interview.view;

public class Trade {

    private String buyProduct;
    private int sellAmount;
    private int buyAmount;

    public Trade() {
    }

    public String getBuyProduct() {
        return buyProduct;
    }

    public void setBuyProduct(String buyProduct) {
        this.buyProduct = buyProduct;
    }

    public int getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Trade trade = (Trade) o;

        if (sellAmount != trade.sellAmount) return false;
        if (buyAmount != trade.buyAmount) return false;
        return buyProduct != null ? buyProduct.equals(trade.buyProduct) : trade.buyProduct == null;
    }

    @Override
    public int hashCode() {
        int result = buyProduct != null ? buyProduct.hashCode() : 0;
        result = 31 * result + sellAmount;
        result = 31 * result + buyAmount;
        return result;
    }
}
