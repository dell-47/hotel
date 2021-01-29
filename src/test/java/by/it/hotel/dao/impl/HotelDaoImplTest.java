package by.it.hotel.dao.impl;

import by.it.hotel.dao.DaoException;
import by.it.hotel.entity.Reservation;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class HotelDaoImplTest {

    @Test
    public void createReservation() throws DaoException {
/*        //Connection connection = DriverManager.getConnection(url, user, password);
        Reservation reservation = new Reservation();
        reservation.setUser(3);  //Nn field
        reservation.setApartTypeId(2);   //Nn field
        reservation.setInDate(LocalDate.parse("2021-01-01"));
        reservation.setOutDate(LocalDate.parse("2021-01-31"));
        reservation.setState("processing");
        reservation.setSubtotalPrice(BigDecimal.valueOf(39));
        reservation.setTaxes(BigDecimal.valueOf(3));
        reservation.setTotalPrice(BigDecimal.valueOf(42));

        HotelDaoImpl hotelDao = new HotelDaoImpl();
        hotelDao.createReservation(reservation);

//        no suitable method to check
//        create some fields
//        retrieve another fields
//        retrieve list of objects
        List<Reservation> reservationList = hotelDao.searchReservations(3);
        for (Reservation res : reservationList) {
            assertEquals(LocalDate.parse("2021-01-01"), res.getInDate());
            assertEquals(LocalDate.parse("2021-01-31"), res.getOutDate());
            assertEquals("processing", res.getState());
            assertEquals(BigDecimal.valueOf(39), res.getSubtotalPrice());
            assertEquals(BigDecimal.valueOf(3), res.getTaxes());
            assertEquals(BigDecimal.valueOf(42), res.getTotalPrice());
        }*/
    }
}