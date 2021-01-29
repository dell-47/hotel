package by.it.hotel.controller.command.util;

import by.it.hotel.entity.User;

import static by.it.hotel.controller.command.impl.CommandConstants.BLANK_STRING;

public class UserUtil {
    public static User getUpdatedUser(User beforeEditUser, User currentUser) {
        User updatedUser = new User();
        updatedUser.setId(beforeEditUser.getId());
        updatedUser.setRole(beforeEditUser.getRole());

        if (BLANK_STRING.equals(currentUser.getFirstName())) {
            updatedUser.setFirstName(beforeEditUser.getFirstName());
        } else {
            updatedUser.setFirstName(currentUser.getFirstName());
        }

        if (BLANK_STRING.equals(currentUser.getLastName())) {
            updatedUser.setLastName(beforeEditUser.getLastName());
        } else {
            updatedUser.setLastName(currentUser.getLastName());
        }

        if (BLANK_STRING.equals(currentUser.getEmail())) {
            updatedUser.setEmail(beforeEditUser.getEmail());
        } else {
            updatedUser.setEmail(currentUser.getEmail());
        }

        return updatedUser;
    }
}
