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
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ApartType> findAparts(LocalDate inDate, LocalDate outDate) throws ServiceException {
        List<ApartType> typeList;
        try {
            typeList = hotelDao.findAparts(inDate, outDate);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return typeList;
    }

    @Override
    public List<ApartType> allAparts() throws ServiceException {
        List<ApartType> typeList;
        try {
            typeList = hotelDao.allAparts();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return typeList;
    }

    @Override
    public ApartType typeById(int id) throws ServiceException {
        ApartType apartType;
        try {
            apartType = hotelDao.typeById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return apartType;
    }

    @Override
    public List<Reservation> reservationsById(int id) throws ServiceException {
        List<Reservation> list;
        try {
            list = hotelDao.reservationsById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<Reservation> reservationsByState() throws ServiceException {
        List<Reservation> list;
        try {
            list = hotelDao.reservationsByState();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<Apart> availableAparts(int id, LocalDate inDate, LocalDate outDate) throws ServiceException {
        List<Apart> list;
        try {
            list = hotelDao.availableAparts(id, inDate, outDate);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public void createInvoice(int reservationId, double price) throws ServiceException {
        try {
            hotelDao.createInvoice(reservationId, price);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateReservation(int reservationId, int apartId) throws ServiceException {
        try {
            hotelDao.updateReservation(reservationId, apartId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Invoice takeInvoice(int reservationId) throws ServiceException {
        Invoice invoice;
        try {
            invoice = hotelDao.takeInvoice(reservationId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return invoice;
    }

    @Override
    public void updateInvoice(int invoiceId) throws ServiceException {
        try {
            hotelDao.updateInvoice(invoiceId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}