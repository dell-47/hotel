package by.it.hotel.dao.impl;

public class DaoConstants {
    private DaoConstants() {}

    static final String CREATE_USER_QUERY = "INSERT INTO users (login, password, first_name, last_name, email, role) VALUES (?,?,?,?,?,?)";
    static final String UPDATE_USER_QUERY = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    static final String RETRIEVE_USER_QUERY = "SELECT * FROM users WHERE login = ?";
    static final String RETRIEVE_ROLE_PERMISSIONS_QUERY = "SELECT pattern FROM roles r LEFT JOIN permissions p ON r.id = p.role WHERE r.id = ?";
    static final String RETRIEVE_ALL_PERMISSIONS_QUERY = "SELECT pattern FROM permissions";
    static final String STATE_CANCELED = "state_canceled";
    static final String STATE_CONFIRMED = "state_confirmed";
    static final String STATE_PROCESSING = "state_processing";
    static final String STATE_DECLINED = "state_declined";
    static final String RETRIEVE_ALL_APART_TYPES_QUERY = "SELECT * FROM aparts_types";
    static final String RETRIEVE_APART_TYPE_QUERY = "SELECT * FROM aparts_types WHERE id=?";
    static final String SEARCH_RESERVATIONS_BY_ID_QUERY = "SELECT * FROM reservations WHERE user_id=?";
    static final String SEARCH_RESERVATIONS_QUERY = "SELECT * FROM reservations r LEFT JOIN aparts_types t ON r.apart_type = t.id WHERE state = ?";
    static final String CREATE_RESERVATION_QUERY = "INSERT INTO reservations (apart_type, in_date, out_date, user_id, state, subtotal_price, taxes, total_price, time) VALUES (?,?,?,?,?,?,?,?,?)";
    static final String UPDATE_RESERVATION_QUERY = "UPDATE reservations SET apart_id = ?, state = ? WHERE id=?";
    static final String UPDATE_RESERVATION_STATE_QUERY = "UPDATE reservations SET state = ? WHERE id=?";
    static final String RETRIEVE_INVOICE_QUERY = "SELECT * FROM reservations r LEFT JOIN aparts_types t ON r.apart_type = t.id WHERE r.id = ?";
    static final String SEARCH_APART_TYPES_QUERY = "SELECT * FROM aparts_types t LEFT JOIN aparts a ON t.id = a.type WHERE a.id NOT IN (SELECT apart_id FROM reservations r WHERE r.state != ? AND r.state != ? AND r.state != ? AND (((r.in_date >= ?) AND (r.in_date < ?)) OR ((r.out_date > ?) AND (r.out_date <= ?)))) GROUP BY t.type ORDER BY t.id ";
    static final String SEARCH_APARTS_QUERY = "SELECT * FROM aparts WHERE type = ? and id not in (SELECT distinct apart_id from reservations r WHERE r.state != ? AND r.state != ? AND r.state != ? AND (((r.in_date >= ?) and (r.in_date < ?)) or ((r.out_date > ?) and (r.out_date <= ?))))";

}
