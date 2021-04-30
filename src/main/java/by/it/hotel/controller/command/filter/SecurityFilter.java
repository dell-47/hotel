package by.it.hotel.controller.command.filter;

import by.it.hotel.controller.command.util.SecurityUtil;
import by.it.hotel.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter implements Filter {
    public static final String LOGIN_PAGE = "/WEB-INF/views/login.jsp";
    public static final String ACCESS_DENIED_PAGE = "/WEB-INF/views/accessDenied.jsp";
    public static final String ERROR_PAGE = "/error.jsp";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        String path = request.getServletPath();

        if (SecurityUtil.isSecurePage(path)) {
            if (user == null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(request, response);
                return;
            }

            int role = user.getRole();
            if (!SecurityUtil.hasPermission(role, path)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ACCESS_DENIED_PAGE);
                requestDispatcher.forward(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
