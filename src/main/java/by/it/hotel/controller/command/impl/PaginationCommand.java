package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.controller.command.utils.PaginationUtil;
import by.it.hotel.entity.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static by.it.hotel.controller.command.impl.CommandConstants.ORDER_OLD_FIRST;


public class PaginationCommand implements Command, SaveRequest {
    private static final Logger logger = LogManager.getLogger(PaginationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        List<Reservation> userReservationList = (List<Reservation>) request.getSession().getAttribute("userReservationList");
        List<Reservation> copyList = new ArrayList<>(userReservationList);
        String order = (String) request.getSession().getAttribute("order");
        if (ORDER_OLD_FIRST.equals(order)) {
            Collections.reverse(copyList);
        }
        int pageNumber = 0;

        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        } catch (NumberFormatException e) {
            logger.error("Invalid request parameters", e);
            response.sendRedirect(CommandConstants.ERROR_PAGE);
            return;
        }

        List<Reservation> paginatedList = PaginationUtil.retrievePaginatedList(copyList, pageNumber);
        request.setAttribute("now", new Date());
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("paginatedList", paginatedList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandConstants.ACCOUNT_PAGE);
        requestDispatcher.forward(request, response);
    }
}

