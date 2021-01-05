package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmReservationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
        String page = CommandConstants.GO_TO_PROFILE_PAGE;

        try {
            hotelService.updateInvoice(invoiceId);
        } catch (ServiceException e) {
            logger.error(e);
            page = CommandConstants.ERROR_PAGE;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
