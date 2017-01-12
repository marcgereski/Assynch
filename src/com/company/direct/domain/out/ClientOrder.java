package com.company.direct.domain.out;

public class ClientOrder extends AbstractClientRequest{
    private final String symbol;
    private final double price;
    private final double volume;

    public ClientOrder(long id, String symbol, double price, double volume) {
        super(id);
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "------------\n" +
                getClass().getSimpleName() +
                "\nOrderId: " + getId() +
                "\nSymbol: " + symbol +
                "\nPrice: " + price +
                "\nVolume: " + volume;
    }
}
