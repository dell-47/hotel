package by.it.hotel.entity;

import java.io.Serializable;

public class ApartType implements Serializable {
    private static final long serialVersionUID = -3906301655372858401L;
    private int id;
    private String type;
    private String description;
    private String image;
    private double price;

    public ApartType(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApartType apartType = (ApartType) o;

        if (Double.compare(apartType.price, price) != 0) return false;
        return type.equals(apartType.type);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ApartType{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
