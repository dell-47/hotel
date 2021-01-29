package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.entity.ApartType;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
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
import java.util.Locale;

import static by.it.hotel.controller.command.impl.CommandConstants.ERROR_PAGE;
import static by.it.hotel.controller.command.impl.CommandConstants.MAIN_PAGE;


public class GoToMainPageCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");
        if (locale == null) {
            locale = Locale.getDefault().getLanguage();
        }
        try {
            List<ApartType> apartList = hotelService.retrieveAllApartTypes(locale);
            session.setAttribute("todayDate", LocalDate.now());
            session.setAttribute("tomorrowDate", LocalDate.now().plusDays(1));
            request.setAttribute("apartList", apartList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Retrieving apart types error", e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
