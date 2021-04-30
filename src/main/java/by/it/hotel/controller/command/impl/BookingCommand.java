package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.entity.*;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class BookingCommand implements Command {
    private static final Logger logger = LogManager.getLogger(BookingCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        String page = GO_TO_SUCCESSFUL_ACTION_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        ApartType apartType = (ApartType) session.getAttribute(APART_ATTRIBUTE);
        CheckOutData checkOutData = (CheckOutData) session.getAttribute(CHECKOUT_DATA_ATTRIBUTE);
        LocalDate inDate = (LocalDate) session.getAttribute(IN_DATE_ATTRIBUTE);
        LocalDate outDate = (LocalDate) session.getAttribute(OUT_DATE_ATTRIBUTE);

        Reservation reservation = new Reservation();
        reservation.setUser(user.getId());
        reservation.setApartTypeId(apartType.getId());
        reservation.setInDate(inDate);
        reservation.setOutDate(outDate);
        reservation.setSubtotalPrice(checkOutData.getSubtotalPrice());
        reservation.setTaxes(checkOutData.getTaxes());
        reservation.setTotalPrice(checkOutData.getTotalPrice());
        reservation.setState(STATE_PROCESSING);

        try {
            hotelService.createReservation(reservation);
            session.setAttribute(ACTION_ATTRIBUTE, ACTION_BOOKING);
        } catch (ServiceException e) {
            logger.error("Booking error", e);
            page = ERROR_PAGE;
        }
        response.sendRedirect(page);
    }
}
