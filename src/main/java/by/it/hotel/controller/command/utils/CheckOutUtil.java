package by.it.hotel.controller.command.utils;

import by.it.hotel.entity.CheckOutData;

import java.time.LocalDate;
import java.time.Period;

public class CheckOutUtil {
    private static final double TAX_RATE = 1.00;

    public static CheckOutData getCheckOutData(LocalDate inDate, LocalDate outDate, double cost) {
        Period period = Period.between(inDate, outDate);
        int days = period.getDays();
        double subtotalPrice = cost * days;
        double taxes = TAX_RATE * days;
        double totalPrice = subtotalPrice + taxes;

        CheckOutData checkOutData = new CheckOutData();
        checkOutData.setSubtotalPrice(subtotalPrice);
        checkOutData.setTaxes(taxes);
        checkOutData.setTotalPrice(totalPrice);

        return checkOutData;
    }

}
