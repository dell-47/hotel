package by.it.hotel.controller;

import by.it.hotel.controller.command.Command;
import by.it.hotel.controller.command.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HotelController extends HttpServlet {

    private static final String PARAMETER_COMMAND = "command";
    private static final String ERROR_PAGE = "error.jsp";
    private static final long serialVersionUID = 1683397206402320305L;
    private final CommandProvider provider = new CommandProvider();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command = provider.getCommand(commandName);
        if (command != null) {
            command.execute(request, response);
        }
        else {
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
