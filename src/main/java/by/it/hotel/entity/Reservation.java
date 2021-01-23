package by.it.hotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation implements Serializable {

    private static final long serialVersionUID = -2553146891301276442L;
    private int id;
    private int num;
    private int apartId;
    private int apartTypeId;
    private int user;
    private int invoice;
    private LocalDate inDate;
    private LocalDate outDate;
    private LocalDateTime time;
    private String apartType;
    private String state;
    private BigDecimal subtotalPrice;
    private BigDecimal totalPrice;
    private BigDecimal taxes;

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

    public BigDecimal getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(BigDecimal subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
        if (!inDate.equals(that.inDate)) return false;
        if (!outDate.equals(that.outDate)) return false;
        if (!time.equals(that.time)) return false;
        if (!apartType.equals(that.apartType)) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (!subtotalPrice.equals(that.subtotalPrice)) return false;
        if (!totalPrice.equals(that.totalPrice)) return false;
        return taxes.equals(that.taxes);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + apartId;
        result = 31 * result + apartTypeId;
        result = 31 * result + user;
        result = 31 * result + invoice;
        result = 31 * result + inDate.hashCode();
        result = 31 * result + outDate.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + apartType.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + subtotalPrice.hashCode();
        result = 31 * result + totalPrice.hashCode();
        result = 31 * result + taxes.hashCode();
        return result;
    }
}
