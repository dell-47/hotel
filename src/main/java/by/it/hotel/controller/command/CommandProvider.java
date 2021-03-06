package by.it.hotel.controller.command;

import by.it.hotel.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("viewAvailable", new SearchAvailableApartsCommand());
        commands.put("registration", new CreateUserCommand());
        commands.put("login", new AuthCommand());
        commands.put("checkout", new CheckoutCommand());
        commands.put("book", new BookingCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("admin", new AdminCommand());
        commands.put("check", new CheckAvailabilityCommand());
        commands.put("confirm", new ConfirmReservationCommand());
        commands.put("invoice", new InvoiceCommand());
        commands.put("pay", new PayCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("signUp", new CreateUserCommand());
        commands.put("goToLoginPage", new GoToLoginPageCommand());
        commands.put("goToSignUpPage", new GoToSignUpPageCommand());
        commands.put("goToMainPage", new GoToMainPageCommand());
        commands.put("successfulAction", new GoToSuccessfulActionPageCommand());
        commands.put("changeLocale", new ChangeLocaleCommand());
        commands.put("bookings", new UserBookingsCommand());
        commands.put("editProfile", new EditProfileCommand());
        commands.put("saveProfile", new SaveProfileCommand());
        commands.put("cancelBooking", new CancelBookingCommand());
        commands.put("declineBooking", new DeclineBookingCommand());
        commands.put("page", new PaginationCommand());
        commands.put("changeOrder", new ChangeOrderCommand());
    }


    public final Command getCommand(final String command) {
        return commands.get(command);
    }
}
