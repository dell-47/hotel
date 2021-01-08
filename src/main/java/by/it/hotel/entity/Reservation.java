package by.it.hotel.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation implements Serializable {

    private static final long serialVersionUID = -2553146891301276442L;
    private int id;
    private int apartId;
    private int apartTypeId;
    private int user;
    private int invoice;
    private LocalDate inDate;
    private LocalDate outDate;
    private LocalDateTime time;
    private String apartType;
    private String state;
    private double subtotalPrice;
    private double totalPrice;
    private double taxes;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApartId() {
        return apartId;
    }

    public void setApartId(int apartId) {
        this.apartId = apartId;
    }

    public int getApartTypeId() {
        return apartTypeId;
    }

    public void setApartTypeId(int apartTypeId) {
        this.apartTypeId = apartTypeId;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(double subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (apartId != that.apartId) return false;
        if (apartTypeId != that.apartTypeId) return false;
        if (user != that.user) return false;
        if (invoice != that.invoice) return false;
        if (Double.compare(that.subtotalPrice, subtotalPrice) != 0) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (Double.compare(that.taxes, taxes) != 0) return false;
        if (!inDate.equals(that.inDate)) return false;
        if (!outDate.equals(that.outDate)) return false;
        if (apartType != null ? !apartType.equals(that.apartType) : that.apartType != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + apartId;
        result = 31 * result + apartTypeId;
        result = 31 * result + user;
        result = 31 * result + invoice;
        result = 31 * result + inDate.hashCode();
        result = 31 * result + outDate.hashCode();
        result = 31 * result + (apartType != null ? apartType.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        temp = Double.doubleToLongBits(subtotalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(taxes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", apartId=" + apartId +
                ", apartTypeId=" + apartTypeId +
                ", user=" + user +
                ", invoice=" + invoice +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                ", apartType='" + apartType + '\'' +
                ", state='" + state + '\'' +
                ", subtotalPrice=" + subtotalPrice +
                ", totalPrice=" + totalPrice +
                ", taxes=" + taxes +
                '}';
    }
}
