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
        HttpSession session = request.getSession();
        String page = AVAILABLE_APARTS_PAGE;
        String locale = (String) request.getSession().getAttribute(LOCALE_ATTRIBUTE);
        if (locale == null) {
            locale = Locale.getDefault().getLanguage();
        }

        try {
            LocalDate inDate = LocalDate.parse(request.getParameter(IN_DATE_ATTRIBUTE));
            LocalDate outDate = LocalDate.parse(request.getParameter(OUT_DATE_ATTRIBUTE));
            List<ApartType> availableAparts = hotelService.searchApartTypes(inDate, outDate, locale);
            session.setAttribute(IN_DATE_ATTRIBUTE, inDate);
            session.setAttribute(OUT_DATE_ATTRIBUTE, outDate);
            request.setAttribute(AVAILABLE_APARTS_ATTRIBUTE, availableAparts);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } catch (DateTimeParseException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(ERROR_PAGE);
        } catch (ValidationException e) {
            if (MAIN.equals(request.getParameter(PAGE_ATTRIBUTE))) {
                page = GO_TO_MAIN_PAGE;
            }
            request.setAttribute(DATES_VALIDATION_ERROR_ATTRIBUTE, DATES_VALIDATION_ERROR_MESSAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Search available aparts error", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
        }
    }
}