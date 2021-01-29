package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.util.EmailUtil;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.User;
import by.it.hotel.service.HotelService;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class PayCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PayCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        HttpSession session = request.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        User user = (User) session.getAttribute("user");
        String page = CommandConstants.GO_TO_ACCOUNT_PAGE;
        EmailUtil.send(invoice, user);
        try {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            hotelService.updateReservation(reservationId, CommandConstants.STATE_PAID);
        } catch (NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            page = CommandConstants.ERROR_PAGE;
        } catch (ServiceException e) {
            logger.error("Pay error", e);
            page = CommandConstants.ERROR_PAGE;
        }
        response.sendRedirect(page);
    }
}
