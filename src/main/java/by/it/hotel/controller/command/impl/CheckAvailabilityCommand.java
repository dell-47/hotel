package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.entity.Apart;
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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


public class CheckAvailabilityCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(CheckAvailabilityCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        List<Apart> availableAparts = null;
        String page = CommandConstants.GO_TO_ADMIN_PAGE;
        int apartTypeId = 0;
        int reservationId = 0;
        LocalDate inDate = null;
        LocalDate outDate = null;

        try {
            apartTypeId = Integer.parseInt(request.getParameter("apartTypeId"));
            reservationId = Integer.parseInt(request.getParameter("reservationId"));
            inDate = LocalDate.parse(request.getParameter("inDate"));
            outDate = LocalDate.parse(request.getParameter("outDate"));
        } catch (DateTimeParseException | NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
            return;
        }

        try {
            availableAparts = hotelService.searchAparts(apartTypeId, inDate, outDate);
        } catch (ServiceException e) {
            logger.error("Check available apartments error",e);
            page = CommandConstants.ERROR_PAGE;
        }

        request.setAttribute("availableAparts", availableAparts);
        request.setAttribute("reservationId", reservationId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
