package com.company.direct.domain.in;

public class OrderTsObject extends AbstractTsObject {
    private final long orderId;
    private final String serial;
    private final String symbol;
    private final double price;
    private final double volume;

    public OrderTsObject(long refId, long orderId, String symbol, double price, double volume) {
        super(refId);
        this.orderId = orderId;
        this.serial = String.valueOf("65365" + orderId);
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
    }

    public String getSerial() {
        return serial;
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

    public long getRefId() {return getId();}

    public long getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "------------\n" +
                getClass().getSimpleName() +
                ":\nRefId: " + getRefId() +
                "\nOrderId: " + orderId +
                "\nSerial: " + serial +
                "\nSymbol: " + symbol +
                "\nPrice: " + price +
                "\nVolume: " + volume;
    }
}
