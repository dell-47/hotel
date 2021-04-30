package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.entity.Reservation;
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
import java.util.List;


public class AdminCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(AdminCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        try {
            List<Reservation> adminList = hotelService.searchReservations();
            request.setAttribute(CommandConstants.ADMIN_LIST_ATTRIBUTE, adminList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandConstants.ADMIN_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Searching reservations error", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
        }
    }
}