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


public class GoToMainPageCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        List<ApartType> apartList = null;
        String page = CommandConstants.MAIN_PAGE;
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");
        if (locale == null) {
            locale = Locale.getDefault().getLanguage();
        }

        try {
            apartList = hotelService.retrieveAllApartTypes(locale);
        } catch (ServiceException e) {
            logger.error("Retrieving apart types error", e);
            page = CommandConstants.ERROR_PAGE;
        }

//        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(new Locale(locale));
        session.setAttribute("todayDate", LocalDate.now());
        session.setAttribute("tomorrowDate", LocalDate.now().plusDays(1));
        request.setAttribute("apartList", apartList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
