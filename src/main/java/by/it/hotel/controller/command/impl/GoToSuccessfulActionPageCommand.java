package by.it.hotel.controller.command.impl;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.SaveRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSuccessfulActionPageCommand implements Command, SaveRequest {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveRequest(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandConstants.SUCCESSFUL_ACTION_PAGE);
        requestDispatcher.forward(request, response);
    }
}
