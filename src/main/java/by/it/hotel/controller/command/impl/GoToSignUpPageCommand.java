package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoToSignUpPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandConstants.SIGN_UP_PAGE);
        requestDispatcher.forward(request, response);
    }
}
