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
}
