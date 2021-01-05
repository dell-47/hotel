package by.it.hotel.entity;

import java.io.Serializable;

public class CheckOutData implements Serializable {
    private static final long serialVersionUID = 3761428628383917818L;
    private double subtotalPrice;
    private double taxes;
    private double totalPrice;

    public CheckOutData(){}

    public double getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(double subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
