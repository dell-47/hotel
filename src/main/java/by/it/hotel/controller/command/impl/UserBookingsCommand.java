package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.controller.command.util.PaginationUtil;
import by.it.hotel.entity.Reservation;
import by.it.hotel.entity.User;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static by.it.hotel.controller.command.impl.CommandConstants.*;


public class UserBookingsCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(UserBookingsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            List<Reservation> userReservationList = hotelService.searchReservations(user.getId());
            Collections.reverse(userReservationList);
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            List<Reservation> paginatedList = PaginationUtil.retrievePaginatedList(userReservationList, pageNumber);
            request.setAttribute("pageNumber", pageNumber);
            session.setAttribute("userReservationList", userReservationList);
            session.setAttribute("order", ORDER_NEWEST_FIRST);
            request.setAttribute("now", new Date());
            request.setAttribute("paginatedList", paginatedList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ACCOUNT_PAGE);
            requestDispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(ERROR_PAGE);
        } catch (ServiceException e) {
            logger.error("Search user bookings error", e);
            response.sendRedirect(ERROR_PAGE);
        }
    }
}