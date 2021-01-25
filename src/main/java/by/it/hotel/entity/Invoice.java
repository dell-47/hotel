package by.it.hotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 2114407498873122086L;

    private int id;
    private LocalDate inDate;
    private LocalDate outDate;
    private String apartType;
    private BigDecimal cost;
    private BigDecimal subtotalPrice;
    private BigDecimal taxes;
    private BigDecimal totalPrice;

    public Invoice(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public String getApartType() {
        return apartType;
    }

    public void setApartType(String apartType) {
        this.apartType = apartType;
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

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (!inDate.equals(invoice.inDate)) return false;
        if (!outDate.equals(invoice.outDate)) return false;
        if (!apartType.equals(invoice.apartType)) return false;
        if (!cost.equals(invoice.cost)) return false;
        if (!subtotalPrice.equals(invoice.subtotalPrice)) return false;
        if (!taxes.equals(invoice.taxes)) return false;
        return totalPrice.equals(invoice.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + inDate.hashCode();
        result = 31 * result + outDate.hashCode();
        result = 31 * result + apartType.hashCode();
        result = 31 * result + cost.hashCode();
        result = 31 * result + subtotalPrice.hashCode();
        result = 31 * result + taxes.hashCode();
        result = 31 * result + totalPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", apartType='" + apartType + '\'' +
                ", cost=" + cost +
                ", subtotalPrice=" + subtotalPrice +
                ", taxes=" + taxes +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
