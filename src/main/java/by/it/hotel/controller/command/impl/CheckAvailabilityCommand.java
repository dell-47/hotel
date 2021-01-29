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

import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class CheckAvailabilityCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(CheckAvailabilityCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();

        try {
            int apartTypeId = Integer.parseInt(request.getParameter("apartTypeId"));
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            LocalDate inDate = LocalDate.parse(request.getParameter("inDate"));
            LocalDate outDate = LocalDate.parse(request.getParameter("outDate"));
            List<Apart> availableAparts = hotelService.searchAparts(apartTypeId, inDate, outDate);
            request.setAttribute("availableAparts", availableAparts);
            request.setAttribute("reservationId", reservationId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GO_TO_ADMIN_PAGE);
            requestDispatcher.forward(request, response);
        } catch (DateTimeParseException | NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(ERROR_PAGE);
        } catch (ServiceException e) {
            logger.error("Check available apartments error",e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
