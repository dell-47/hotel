package by.it.hotel.dao;

import by.it.hotel.dao.impl.HotelDaoImpl;
import by.it.hotel.dao.impl.UserDaoImpl;

public class DaoProvider {

    private static final DaoProvider instance = new DaoProvider();
    private final HotelDao hotelDao = new HotelDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    public DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public HotelDao getHotelDao() {
        return hotelDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
