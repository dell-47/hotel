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
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class SearchAvailableApartsCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(SearchAvailableApartsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        List<ApartType> availableAparts = null;
        String page = AVAILABLE_APARTS_PAGE;
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == null) {
            locale = Locale.getDefault().getLanguage();
        }
        LocalDate inDate;
        LocalDate outDate;
        try {
            inDate = LocalDate.parse(request.getParameter("inDate"));
            outDate = LocalDate.parse(request.getParameter("outDate"));
        } catch (DateTimeParseException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
            return;
        }

        try {
            availableAparts = hotelService.searchApartTypes(inDate, outDate, locale);
        } catch (ValidationException e) {
            if (MAIN.equals(request.getParameter("page"))) {
                page = GO_TO_MAIN_PAGE;
            }
            request.setAttribute("datesValidationError", DATES_VALIDATION_ERROR_MESSAGE);
        } catch (ServiceException e) {
            logger.error("Search available aparts error", e);
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