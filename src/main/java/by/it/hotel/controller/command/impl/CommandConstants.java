package by.it.hotel.controller.command.impl;

public final class CommandConstants {
    private CommandConstants() {}

    public static final String INDEX_PAGE = "/index.jsp";
    public static final String ERROR_PAGE = "/error.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/views/login.jsp";
    public static final String APPROVED_PAGE = "/WEB-INF/views/approved.jsp";
    public static final String MAIN_PAGE = "/WEB-INF/views/main.jsp";
    public static final String ADMIN_PAGE = "/WEB-INF/views/admin.jsp";
    public static final String GO_TO_ADMIN_PAGE = "/hotel/admin?command=admin";
    public static final String CHECKOUT_PAGE = "/WEB-INF/views/checkout.jsp";
    public static final String SIGN_UP_PAGE = "/WEB-INF/views/signUp.jsp";
    public static final String INVOICE_PAGE = "/WEB-INF/views/invoice.jsp";
    public static final String PROFILE_PAGE = "/WEB-INF/views/profile.jsp";
    public static final String GO_TO_PROFILE_PAGE = "/profile?command=profile";
    public static final String AVAILABLE_APARTS_PAGE = "/WEB-INF/views/availableAparts.jsp";
    public static final String LOGIN_ERROR_MESSAGE = "Invalid login or password. Try again.";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
}
