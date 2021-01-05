package by.it.hotel.service;

import by.it.hotel.dao.DaoException;
import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    void createReservation(Reservation reservation) throws ServiceException;
    List<ApartType> findAparts(LocalDate inDate, LocalDate outDate) throws ServiceException;
    List<ApartType> allAparts() throws ServiceException;
    ApartType typeById(int id) throws ServiceException;
    List<Reservation> reservationsById(int id) throws ServiceException;
    List<Reservation> reservationsByState() throws ServiceException;
    List<Apart> availableAparts(int id, LocalDate inDate, LocalDate outDate) throws ServiceException;
    void createInvoice(int reservationId, double price) throws ServiceException;
    void updateReservation(int reservationId, int apartId) throws ServiceException;
    Invoice takeInvoice(int reservationId) throws ServiceException;
    void updateInvoice(int invoiceId) throws ServiceException;
}
