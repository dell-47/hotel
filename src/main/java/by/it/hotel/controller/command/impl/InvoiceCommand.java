package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.entity.Invoice;
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


public class InvoiceCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(InvoiceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        HotelService hotelService = serviceProvider.getHotelService();
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        Invoice invoice = null;
        String page = CommandConstants.INVOICE_PAGE;

        try {
            invoice = hotelService.retrieveInvoice(reservationId);
        } catch (ServiceException e) {
            logger.error(e);
            page = CommandConstants.ERROR_PAGE;
        }

        request.getSession().setAttribute("invoice", invoice);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}