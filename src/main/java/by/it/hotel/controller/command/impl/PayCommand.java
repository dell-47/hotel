package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.utils.EmailUtil;
import by.it.hotel.entity.Invoice;
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


public class PayCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PayCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        HttpSession session = request.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        User user = (User) session.getAttribute("user");
        String page = CommandConstants.GO_TO_PROFILE_PAGE;
        EmailUtil.send(invoice, user);

        try {
            hotelService.updateReservation(reservationId);
        } catch (ServiceException e) {
            logger.error(e);
            page = CommandConstants.ERROR_PAGE;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
