package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
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
import static by.it.hotel.controller.command.impl.CommandConstants.*;

public class AdminCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        List<Reservation> reservationList = null;
        String page = ADMIN_PAGE;

        try {
            reservationList = hotelService.reservationsByState();
        } catch (ServiceException e) {
            logger.error(e);
            page = ERROR_PAGE;
        }

        request.setAttribute("adminList", reservationList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
