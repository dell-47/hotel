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


public class ConfirmReservationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmReservationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        int apartId = Integer.parseInt(request.getParameter("apartId"));
        double price = Double.parseDouble(request.getParameter("price"));
        String page = CommandConstants.GO_TO_ADMIN_PAGE;

        try {
  //          hotelService.createInvoice(reservationId, price);
            hotelService.updateReservation(reservationId, apartId);
        } catch (ServiceException e) {
            logger.error(e);
            page = CommandConstants.ERROR_PAGE;
        }

        response.sendRedirect(page);
    }
}
