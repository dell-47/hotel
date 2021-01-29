package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.controller.command.util.CheckOutUtil;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.CheckOutData;
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
import java.time.format.DateTimeParseException;


public class CheckoutCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(CheckoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        HttpSession session = request.getSession();
        try {
            LocalDate inDate = LocalDate.parse(request.getParameter("inDate"));
            LocalDate outDate = LocalDate.parse(request.getParameter("outDate"));
            int apartTypeId = Integer.parseInt(request.getParameter("id"));
            ApartType apartType = hotelService.retrieveApartType(apartTypeId);
            CheckOutData checkOutData = CheckOutUtil.getCheckOutData(inDate, outDate, apartType.getPrice());
            session.setAttribute("apart", apartType);
            session.setAttribute("checkOutData", checkOutData);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandConstants.CHECKOUT_PAGE);
            requestDispatcher.forward(request, response);
        } catch (DateTimeParseException | NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
        } catch (ServiceException e) {
            logger.error("Checkout error", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
        }
    }
}