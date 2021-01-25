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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckOutData that = (CheckOutData) o;

        if (nights != that.nights) return false;
        if (!cost.equals(that.cost)) return false;
        if (!subtotalPrice.equals(that.subtotalPrice)) return false;
        if (!taxes.equals(that.taxes)) return false;
        return totalPrice.equals(that.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = nights;
        result = 31 * result + cost.hashCode();
        result = 31 * result + subtotalPrice.hashCode();
        result = 31 * result + taxes.hashCode();
        result = 31 * result + totalPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CheckOutData{" +
                "nights=" + nights +
                ", cost=" + cost +
                ", subtotalPrice=" + subtotalPrice +
                ", taxes=" + taxes +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
