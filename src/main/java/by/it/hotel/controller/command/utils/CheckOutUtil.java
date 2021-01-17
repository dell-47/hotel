package by.it.hotel.controller.command.utils;

import by.it.hotel.entity.CheckOutData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class CheckOutUtil {
    private static final double TAX_RATE = 1.00;

    public static CheckOutData getCheckOutData(LocalDate inDate, LocalDate outDate, double apartCost) {
        Period period = Period.between(inDate, outDate);
        int days = period.getDays();
        BigDecimal cost = BigDecimal.valueOf(apartCost).setScale(2);
        BigDecimal subtotalPrice = BigDecimal.valueOf(apartCost * days).setScale(2);
        BigDecimal taxes = BigDecimal.valueOf(TAX_RATE * days).setScale(2);
        BigDecimal totalPrice = subtotalPrice.add(taxes).setScale(2);

        CheckOutData checkOutData = new CheckOutData();
        checkOutData.setCost(cost);
        checkOutData.setSubtotalPrice(subtotalPrice);
        checkOutData.setTaxes(taxes);
        checkOutData.setTotalPrice(totalPrice);

        return checkOutData;
    }

}
