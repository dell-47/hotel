package by.it.hotel.service.impl;

import by.it.hotel.dao.DaoException;
import by.it.hotel.dao.DaoProvider;
import by.it.hotel.dao.HotelDao;
import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.validation.DatesValidator;
import by.it.hotel.service.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class HotelServiceImpl implements HotelService {
    private static final Logger logger = LogManager.getLogger(HotelServiceImpl.class);

    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final HotelDao hotelDao = daoProvider.getHotelDao();

    @Override
    public void createReservation(Reservation reservation) throws ServiceException {
        try {
            hotelDao.createReservation(reservation);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ApartType> searchApartTypes(LocalDate inDate, LocalDate outDate, String locale) throws ServiceException {

        if (!DatesValidator.isCorrectDates(inDate, outDate)) {
            logger.info("Dates validation error");
            throw new ValidationException();
        }

        List<ApartType> typeList;
        try {
            typeList = hotelDao.searchApartTypes(inDate, outDate, locale);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return typeList;
    }

    @Override
    public List<ApartType> retrieveAllApartTypes(String locale) throws ServiceException {
        List<ApartType> typeList;
        try {
            typeList = hotelDao.retrieveAllApartTypes(locale);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return typeList;
    }

    @Override
    public ApartType retrieveApartType(int id) throws ServiceException {
        ApartType apartType;
        try {
            apartType = hotelDao.retrieveApartType(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return apartType;
    }

    @Override
    public List<Reservation> searchReservations(int id) throws ServiceException {
        List<Reservation> list;
        try {
            list = hotelDao.searchReservations(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<Reservation> searchReservations() throws ServiceException {
        List<Reservation> list;
        try {
            list = hotelDao.searchReservations();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<Apart> searchAparts(int id, LocalDate inDate, LocalDate outDate) throws ServiceException {

        if (!DatesValidator.isCorrectDates(inDate, outDate)) {
            logger.info("Dates validation error");
            throw new ValidationException();
        }

        List<Apart> list;
        try {
            list = hotelDao.searchAparts(id, inDate, outDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public void updateReservation(int reservationId, int apartId) throws ServiceException {
        try {
            hotelDao.updateReservation(reservationId, apartId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Invoice retrieveInvoice(int reservationId) throws ServiceException {
        Invoice invoice;
        try {
            invoice = hotelDao.retrieveInvoice(reservationId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return invoice;
    }

    @Override
    public void updateReservation(int reservationId, String cause) throws ServiceException {
        try {
            hotelDao.updateReservation(reservationId, cause);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}