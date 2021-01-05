package by.it.hotel.service;

import by.it.hotel.service.impl.HotelServiceImpl;
import by.it.hotel.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final HotelService hotelService = new HotelServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public UserService getUserService() {
        return userService;
    }
}
