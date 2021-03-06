package by.it.hotel.service;

import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    void createReservation(Reservation reservation) throws ServiceException;
    void updateReservation(int reservationId, String cause) throws ServiceException;
    void updateReservation(int reservationId, int apartId) throws ServiceException;
    ApartType retrieveApartType(int id) throws ServiceException;
    Invoice retrieveInvoice(int reservationId) throws ServiceException;
    List<Apart> searchAparts(int id, LocalDate inDate, LocalDate outDate) throws ServiceException;
    List<ApartType> searchApartTypes(LocalDate inDate, LocalDate outDate, String locale) throws ServiceException;
    List<ApartType> retrieveAllApartTypes(String locale) throws ServiceException;
    List<Reservation> searchReservations() throws ServiceException;
    List<Reservation> searchReservations(int id) throws ServiceException;
}
