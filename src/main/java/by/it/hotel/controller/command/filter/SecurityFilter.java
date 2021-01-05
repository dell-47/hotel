package by.it.hotel.controller.command.filter;

import by.it.hotel.entity.User;
import by.it.hotel.service.ServiceException;
import by.it.hotel.service.ServiceProvider;
import by.it.hotel.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(SecurityFilter.class);

    public static final String LOGIN_PAGE = "/WEB-INF/views/login.jsp";
    public static final String ACCESS_DENIED_PAGE = "/WEB-INF/views/accessDenied.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = null;
        user = (User) request.getSession().getAttribute("user");
        String path = request.getServletPath();

        if (path.equals("/approved.jsp") || path.equals("/main") || path.equals("/index.jsp") || path.equals("/login") || path.equals("/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (user == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        int role = user.getRole();
        List<String> permissions = null;
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        try {
            permissions = userService.rolePermissions(role);
        } catch (ServiceException e) {
            logger.error(e);
        }

        if (permissions != null && !permissions.contains(path)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ACCESS_DENIED_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
