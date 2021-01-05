package by.it.hotel.service.validation;

import java.time.LocalDate;
import java.time.Period;

public final class DatesValidator {

    private DatesValidator() {
    }

    public static boolean isCorrectDates(LocalDate inDate, LocalDate outDate) {

        if (Period.between(inDate, outDate).getDays() < 1) {
            return false;
        };

        return Period.between(LocalDate.now(), inDate).getDays() >= 0;
    }
}
