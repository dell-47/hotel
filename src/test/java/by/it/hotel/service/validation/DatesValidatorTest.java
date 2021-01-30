package by.it.hotel.service.validation;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DatesValidatorTest {

    @Test
    public void testIsCorrectDates() {
        assertTrue(DatesValidator.isCorrectDates(LocalDate.now(), LocalDate.now().plusDays(5)));
        assertTrue(DatesValidator.isCorrectDates(LocalDate.now().plusDays(2), LocalDate.now().plusDays(3)));
        assertFalse(DatesValidator.isCorrectDates(LocalDate.now(), LocalDate.now()));
        assertFalse(DatesValidator.isCorrectDates(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)));
        assertFalse(DatesValidator.isCorrectDates(LocalDate.now(), LocalDate.now().minusDays(1)));
        assertFalse(DatesValidator.isCorrectDates(LocalDate.now().plusDays(4), LocalDate.now().plusDays(3)));
    }
}