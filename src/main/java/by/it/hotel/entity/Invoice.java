package by.it.hotel.entity;

public class Invoice {
    private int id;
    private int reservationId;
    private String state;
    private double amount;

    public Invoice(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (reservationId != invoice.reservationId) return false;
        if (Double.compare(invoice.amount, amount) != 0) return false;
        return state.equals(invoice.state);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + reservationId;
        result = 31 * result + state.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", reservationId=" + reservationId +
                ", state='" + state + '\'' +
                ", amount=" + amount +
                '}';
    }
}
