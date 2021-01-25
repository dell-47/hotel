package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ConfirmReservationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmReservationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        String page = CommandConstants.GO_TO_ADMIN_PAGE;
        int apartId = 0;
        int reservationId = 0;

        try {
            reservationId = Integer.parseInt(request.getParameter("reservationId"));
            apartId = Integer.parseInt(request.getParameter("apartId"));
        } catch (NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
            return;
        }

        try {
            hotelService.updateReservation(reservationId, apartId);
        } catch (ServiceException e) {
            logger.error("Booking confirmation error",e);
            page = CommandConstants.ERROR_PAGE;
        }

        response.sendRedirect(page);
    }
}
