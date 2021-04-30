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

import static by.it.hotel.controller.command.impl.CommandConstants.*;

public class CancelBookingCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CancelBookingCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        try {
            int reservationId = Integer.parseInt(request.getParameter(RESERVATION_ID_ATTRIBUTE));
            hotelService.updateReservation(reservationId, STATE_CANCELED);
            response.sendRedirect(GO_TO_ACCOUNT_PAGE);
        } catch (NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(ERROR_PAGE);
        } catch (ServiceException e) {
            logger.error("Booking cancellation error", e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
