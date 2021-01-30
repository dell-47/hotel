package by.it.hotel.controller.command.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityUtilTest {

    @Test
    public void testIsSecurePage() {
        assertTrue(SecurityUtil.isSecurePage("/admin"));
        assertTrue(SecurityUtil.isSecurePage("/profile"));
        assertTrue(SecurityUtil.isSecurePage("/bookings"));
        assertTrue(SecurityUtil.isSecurePage("/invoice"));
        assertTrue(SecurityUtil.isSecurePage("/pay"));
        assertFalse(SecurityUtil.isSecurePage("/login"));
        assertFalse(SecurityUtil.isSecurePage("/signUp"));
        assertFalse(SecurityUtil.isSecurePage("/main"));
    }


    /**
     *  Checks the role permission.
     *  Where roleId 1 - admin, 2 - user
     */
    @Test
    public void testHasPermission() {
        assertTrue(SecurityUtil.hasPermission(1,"/admin"));
        assertFalse(SecurityUtil.hasPermission(1,"/profile"));
        assertFalse(SecurityUtil.hasPermission(1,"/bookings"));
        assertFalse(SecurityUtil.hasPermission(1,"/invoice"));

        assertFalse(SecurityUtil.hasPermission(2,"/admin"));
        assertTrue(SecurityUtil.hasPermission(2,"/profile"));
        assertTrue(SecurityUtil.hasPermission(2,"/bookings"));
        assertTrue(SecurityUtil.hasPermission(2,"/invoice"));
    }
}