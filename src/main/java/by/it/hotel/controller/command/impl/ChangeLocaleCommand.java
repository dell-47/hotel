package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.hotel.controller.command.impl.CommandConstants.*;

public class ChangeLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(LOCALE_ATTRIBUTE, request.getParameter(LANGUAGE_PARAMETER));
        String savedRequest = (String) request.getSession().getAttribute(SAVED_REQUEST_ATTRIBUTE);
        response.sendRedirect(savedRequest);
    }
}
