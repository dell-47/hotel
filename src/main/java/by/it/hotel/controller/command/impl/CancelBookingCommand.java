package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CancelBookingCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CancelBookingCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        String page = CommandConstants.GO_TO_ACCOUNT_PAGE;

        try {
            hotelService.updateReservation(reservationId, CommandConstants.STATE_CANCELED);
        } catch (ServiceException e) {
            logger.error(e);
            page = CommandConstants.ERROR_PAGE;
        }
        response.sendRedirect(page);
    }
}
