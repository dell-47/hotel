package by.it.hotel.controller.command.impl;

public final class CommandConstants {
    private CommandConstants() {}

    public static final String INDEX_PAGE = "/index.jsp";
    public static final String ERROR_PAGE = "/error.jsp";
    public static final String ACTION_SIGN_UP = "signUp";
    public static final String ACTION_BOOKING = "booking";
    public static final String SUCCESSFUL_ACTION_PAGE = "successfulAction.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/views/login.jsp";
    public static final String MAIN_PAGE = "/WEB-INF/views/main.jsp";
    public static final String GO_TO_MAIN_PAGE = "/main?command=goToMainPage";
    public static final String GO_TO_SIGN_UP_PAGE = "/signUp?command=goToSignUpPage";
    public static final String ADMIN_PAGE = "/WEB-INF/views/admin.jsp";
    public static final String GO_TO_ADMIN_PAGE = "/hotel/admin?command=admin";
    public static final String CHECKOUT_PAGE = "/WEB-INF/views/checkout.jsp";
    public static final String SIGN_UP_PAGE = "/WEB-INF/views/signUp.jsp";
    public static final String INVOICE_PAGE = "/WEB-INF/views/invoice.jsp";
    public static final String PROFILE_PAGE = "/WEB-INF/views/profile.jsp";
    public static final String GO_TO_PROFILE_PAGE = "/profile?command=profile";
    public static final String AVAILABLE_APARTS_PAGE = "/WEB-INF/views/availableAparts.jsp";
    public static final String LOGIN_ERROR_MESSAGE = "Invalid login or password. Try again.";
    public static final String USERNAME_ERROR_MESSAGE = "Entered username isn't allowed. Try again.";
    public static final String DATES_VALIDATION_ERROR_MESSAGE = "Invalid dates entered. Try again with correct dates.";
    public static final String STATE_PROCESSING = "processing";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String BLANK_STRING = "";
    public static final int ROLE_USER = 2;

}
