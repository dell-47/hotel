package by.it.hotel.dao;

import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;

import java.time.LocalDate;
import java.util.List;


public interface HotelDao {
    void createReservation(Reservation reservation) throws DaoException;
    List<ApartType> findAparts(LocalDate inDate, LocalDate outDate) throws DaoException;
    List<ApartType> allAparts() throws DaoException;
    ApartType typeById(int id) throws DaoException;
    List<Reservation> reservationsById(int id) throws DaoException;
    List<Reservation> reservationsByState() throws DaoException;
    List<Apart> availableAparts(int id, LocalDate inDate, LocalDate outDate) throws DaoException;
    void createInvoice(int reservationId, double price) throws DaoException;
    void updateReservation(int reservationId, int apartId) throws DaoException;
    Invoice takeInvoice(int reservationId) throws DaoException;
    void updateInvoice(int invoiceId) throws DaoException;
}
