package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;
import by.it.hotel.controller.command.util.PaginationUtil;
import by.it.hotel.entity.Reservation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static by.it.hotel.controller.command.impl.CommandConstants.*;

public class ChangeOrderCommand implements Command, SaveRequest {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        List<Reservation> userReservationList = (List<Reservation>) request.getSession().getAttribute(USER_RESERVATION_LIST_ATTRIBUTE);
        List<Reservation> copyList = new ArrayList<>(userReservationList);
        String order = request.getParameter(ORDER_ATTRIBUTE);
        if (ORDER_OLDEST_FIRST.equals(order)) {
            Collections.reverse(copyList);
        }
        List<Reservation> paginatedList = PaginationUtil.retrievePaginatedList(copyList, FIRST_PAGE);
        request.setAttribute(NOW_DATE_ATTRIBUTE, new Date());
        request.setAttribute(PAGE_NUMBER_ATTRIBUTE, FIRST_PAGE);
        request.setAttribute(PAGINATED_LIST_ATTRIBUTE, paginatedList);
        request.getSession().setAttribute(ORDER_ATTRIBUTE, order);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ACCOUNT_PAGE);
        requestDispatcher.forward(request, response);
    }
}
