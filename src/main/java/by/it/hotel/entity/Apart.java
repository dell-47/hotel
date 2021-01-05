package by.it.hotel.entity;

import java.io.Serializable;

public class Apart implements Serializable {
    private static final long serialVersionUID = 7956265574401622306L;
    private int id;
    private int num;

    public Apart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        Apart apart = (Apart) o;

        if (id != apart.id) return false;
        return num == apart.num;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + num;
        return result;
    }

    @Override
    public String toString() {
        return "Apart{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
