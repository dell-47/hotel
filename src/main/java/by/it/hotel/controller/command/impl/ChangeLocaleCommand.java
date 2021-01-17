package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("locale", request.getParameter("lang"));
        String savedRequest = (String) request.getSession().getAttribute("savedRequest");
        //System.out.println(savedRequest);
        response.sendRedirect(savedRequest);
    }
}
