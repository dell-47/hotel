package by.it.hotel.controller.command.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaginationUtilTest {

    @Test
    public void testGetPageCount() {
        assertEquals(1, PaginationUtil.getPageCount(2));
        assertEquals(1, PaginationUtil.getPageCount(5));
        assertEquals(2, PaginationUtil.getPageCount(6));
        assertEquals(2, PaginationUtil.getPageCount(10));
        assertEquals(3, PaginationUtil.getPageCount(11));
    }
}