package by.it.hotel.controller.command;

import by.it.hotel.controller.command.impl.CommandConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public interface SaveRequest {

    default void saveRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        if (servletPath.matches(CommandConstants.POSTFIX_JSP)) {
            session.setAttribute("savedRequest", servletPath);
            return;
        }

        StringBuilder output = new StringBuilder(servletPath).append("?");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String param = paramNames.nextElement();
            String value = request.getParameter(param);
            output.append(param).append("=").append(value).append("&");
        }
        session.setAttribute("savedRequest", output.deleteCharAt(output.length()-1).toString());
    }
}