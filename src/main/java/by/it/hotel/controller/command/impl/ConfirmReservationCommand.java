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
        try {
            int reservationId = Integer.parseInt(request.getParameter(CommandConstants.RESERVATION_ID_ATTRIBUTE));
            int apartId = Integer.parseInt(request.getParameter(CommandConstants.APART_ID_ATTRIBUTE));
            hotelService.updateReservation(reservationId, apartId);
        } catch (NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            page = CommandConstants.ERROR_PAGE;
        } catch (ServiceException e) {
            logger.error("Booking confirmation error",e);
            page = CommandConstants.ERROR_PAGE;
        }
        response.sendRedirect(page);
    }
}
