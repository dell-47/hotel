package by.it.hotel.dao;

import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;

import java.time.LocalDate;
import java.util.List;


public interface HotelDao {
    void createReservation(Reservation reservation) throws DaoException;
    void updateReservation(int reservationId) throws DaoException;
    void updateReservation(int reservationId, int apartId) throws DaoException;
    ApartType retrieveApartType(int id) throws DaoException;
    Invoice retrieveInvoice(int reservationId) throws DaoException;
    List<Apart> searchAparts(int id, LocalDate inDate, LocalDate outDate) throws DaoException;
    List<ApartType> searchApartTypes(LocalDate inDate, LocalDate outDate) throws DaoException;
    List<ApartType> retrieveAllApartTypes() throws DaoException;
    List<Reservation> searchReservations() throws DaoException;
    List<Reservation> searchReservations(int id) throws DaoException;
}
