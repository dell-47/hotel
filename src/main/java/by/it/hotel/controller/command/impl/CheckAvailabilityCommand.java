package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.entity.Apart;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class CheckAvailabilityCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CheckAvailabilityCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        List<Apart> availableAparts = null;
        int id = Integer.parseInt(request.getParameter("id"));
        LocalDate inDate = LocalDate.parse(request.getParameter("inDate"));
        LocalDate outDate = LocalDate.parse(request.getParameter("outDate"));
        String page = CommandConstants.GO_TO_ADMIN_PAGE;
        try {
            availableAparts = hotelService.searchAparts(id, inDate, outDate);
        } catch (ServiceException e) {
            logger.error(e);
            page = CommandConstants.ERROR_PAGE;
        }

        request.setAttribute("availableAparts", availableAparts);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
