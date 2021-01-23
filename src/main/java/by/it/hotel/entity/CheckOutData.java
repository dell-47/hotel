package by.it.hotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CheckOutData implements Serializable {
    private static final long serialVersionUID = 3761428628383917818L;

    private int nights;
    private BigDecimal cost;
    private BigDecimal subtotalPrice;
    private BigDecimal taxes;
    private BigDecimal totalPrice;

    public CheckOutData(){}

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(BigDecimal subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
