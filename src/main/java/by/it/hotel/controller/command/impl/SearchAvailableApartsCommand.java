package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.entity.ApartType;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class SearchAvailableApartsCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(SearchAvailableApartsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        List<ApartType> availableAparts = null;
        LocalDate inDate = LocalDate.parse(request.getParameter("inDate"));
        LocalDate outDate = LocalDate.parse(request.getParameter("outDate"));
        String page = AVAILABLE_APARTS_PAGE;

        try {
            availableAparts = hotelService.searchApartTypes(inDate, outDate);
        } catch (ValidationException e) {
            page = GO_TO_MAIN_PAGE;
            request.setAttribute("datesValidationError", DATES_VALIDATION_ERROR_MESSAGE);
        } catch (ServiceException e) {
            logger.error("Reservation error", e);
            page = ERROR_PAGE;
        }

        HttpSession session = request.getSession();
        session.setAttribute("inDate", inDate);
        session.setAttribute("outDate", outDate);
        request.setAttribute("availableAparts", availableAparts);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
