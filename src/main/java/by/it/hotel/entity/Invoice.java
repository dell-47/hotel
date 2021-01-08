package by.it.hotel.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 2114407498873122086L;

    private int id;
    private LocalDate inDate;
    private LocalDate outDate;
    private String apartType;
    private double cost;
    private double subtotalPrice;
    private double taxes;
    private double totalPrice;

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

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
