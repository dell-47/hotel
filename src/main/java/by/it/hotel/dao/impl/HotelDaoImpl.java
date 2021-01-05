package by.it.hotel.dao.impl;

import by.it.hotel.dao.DaoException;
import by.it.hotel.dao.HotelDao;
import by.it.hotel.dao.pool.ConnectionPool;
import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao {
    private static final Logger logger = LogManager.getLogger(HotelDaoImpl.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String STATE_PAID = "paid";
    private static final String STATE_CONFIRMED = "confirmed";
    private static final String STATE_WAITING_FOR_PAYMENT = "waiting for payment";
    private static final String RETRIEVE_ALL_APART_TYPES_QUERY = "SELECT * FROM aparts_types";
    private static final String RETRIEVE_APART_TYPE_QUERY = "SELECT * FROM aparts_types WHERE id=?";
    private static final String SEARCH_RESERVATIONS_BY_ID_QUERY = "SELECT * FROM reservations WHERE user_id=?";
    private static final String SEARCH_RESERVATIONS_QUERY = "SELECT * FROM reservations r LEFT JOIN aparts_types t ON r.apart_type = t.id WHERE state = 'processing'";
    private static final String CREATE_RESERVATION_QUERY = "INSERT INTO reservations (apart_type, in_date, out_date, user_id, state, subtotal_price, taxes, total_price) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_RESERVATION_QUERY = "UPDATE reservations SET apart_id = ?, state = ? WHERE id=?";
    private static final String RETRIEVE_INVOICE_QUERY = "SELECT * FROM invoices WHERE reservation_id = ?";
    private static final String CREATE_INVOICE_QUERY = "INSERT INTO invoices (amount, state, reservation_id) VALUES (?,?,?)";
    private static final String UPDATE_INVOICE_QUERY = "UPDATE invoices i LEFT JOIN reservations r ON i.reservation_id = r.id SET i.state = ?, r.state = ? WHERE i.id = ?";
    private static final String SEARCH_APART_TYPES_QUERY = "SELECT * FROM aparts_types t LEFT JOIN aparts a ON t.id = a.type WHERE a.id NOT IN (SELECT apart_id FROM reservations r WHERE r.state != 'processing' AND (((r.in_date >= ?) AND (r.in_date < ?)) OR ((r.out_date > ?) AND (r.out_date <= ?)))) GROUP BY t.type ORDER BY t.id ";
    private static final String SEARCH_APARTS_QUERY = "SELECT * FROM aparts WHERE type = ? and id not in (SELECT distinct apart_id from reservations r WHERE r.state != 'processing' AND (((r.in_date >= ?) and (r.in_date < ?)) or ((r.out_date > ?) and (r.out_date <= ?))))";

    @Override
    public void createReservation(Reservation reservation) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(CREATE_RESERVATION_QUERY);

            ps.setInt(1, reservation.getApartTypeId());
            ps.setDate(2, java.sql.Date.valueOf(reservation.getInDate()));
            ps.setDate(3, java.sql.Date.valueOf(reservation.getOutDate()));
            ps.setInt(4, reservation.getUser());
            ps.setString(5, reservation.getState());
            ps.setDouble(6, reservation.getSubtotalPrice());
            ps.setDouble(7, reservation.getTaxes());
            ps.setDouble(8, reservation.getTotalPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public List<ApartType> searchApartTypes(LocalDate inDate, LocalDate outDate) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ApartType> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(SEARCH_APART_TYPES_QUERY);

            ps.setDate(1, java.sql.Date.valueOf(inDate));
            ps.setDate(2, java.sql.Date.valueOf(outDate));
            ps.setDate(3, java.sql.Date.valueOf(inDate));
            ps.setDate(4, java.sql.Date.valueOf(outDate));

            rs = ps.executeQuery();
            while (rs.next()) {
                ApartType type = new ApartType();
                type.setId(rs.getInt("id"));
                type.setPrice(rs.getDouble("price"));
                type.setDescription(rs.getString("description"));
                type.setImage(rs.getString("image"));
                list.add(type);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<ApartType> retrieveAllApartTypes() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<ApartType> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(RETRIEVE_ALL_APART_TYPES_QUERY);
            while (rs.next()) {
                ApartType type = new ApartType();
                type.setId(rs.getInt("id"));
                type.setType(rs.getString("type"));
                type.setPrice(rs.getDouble("price"));
                type.setDescription(rs.getString("description"));
                type.setImage(rs.getString("image"));
                list.add(type);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
        return list;
    }


    @Override
    public ApartType retrieveApartType(int id) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ApartType apartType = null;

        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(RETRIEVE_APART_TYPE_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                apartType = new ApartType();
                apartType.setId(rs.getInt("id"));
                apartType.setType(rs.getString("type"));
                apartType.setPrice(rs.getDouble("price"));
                apartType.setDescription(rs.getString("description"));
                apartType.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return apartType;
    }

    @Override
    public List<Reservation> searchReservations(int id) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reservation> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(SEARCH_RESERVATIONS_BY_ID_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setInDate(rs.getDate("in_date").toLocalDate());
                reservation.setOutDate(rs.getDate("out_date").toLocalDate());
                reservation.setState(rs.getString("state"));
                reservation.setSubtotalPrice(rs.getDouble("subtotal_price"));
                reservation.setTaxes(rs.getDouble("taxes"));
                reservation.setTotalPrice(rs.getDouble("total_price"));
                list.add(reservation);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<Reservation> searchReservations() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<Reservation> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SEARCH_RESERVATIONS_QUERY);

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setInDate(rs.getDate("in_date").toLocalDate());
                reservation.setOutDate(rs.getDate("out_date").toLocalDate());
                reservation.setApartTypeId(rs.getInt("apart_type"));
                reservation.setApartType(rs.getString("type"));
                reservation.setSubtotalPrice(rs.getDouble("subtotal_price"));
                reservation.setTaxes(rs.getDouble("taxes"));
                reservation.setTotalPrice(rs.getDouble("total_price"));
                list.add(reservation);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
        return list;
    }

    @Override
    public List<Apart> searchAparts(int id, LocalDate inDate, LocalDate outDate) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Apart> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(SEARCH_APARTS_QUERY);
            ps.setInt(1, id);
            ps.setDate(2, java.sql.Date.valueOf(inDate));
            ps.setDate(3, java.sql.Date.valueOf(outDate));
            ps.setDate(4, java.sql.Date.valueOf(inDate));
            ps.setDate(5, java.sql.Date.valueOf(outDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                Apart apart = new Apart();
                apart.setId(rs.getInt("id"));
                apart.setNum(rs.getInt("num"));
                list.add(apart);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return list;
    }

    @Override
    public void createInvoice(int reservationId, double price) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(CREATE_INVOICE_QUERY);

            ps.setDouble(1, price);
            ps.setString(2, STATE_WAITING_FOR_PAYMENT);
            ps.setInt(3, reservationId);

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public void updateReservation(int reservationId, int apartId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(UPDATE_RESERVATION_QUERY);
            ps.setInt(1, apartId);
            ps.setString(2, STATE_CONFIRMED);
            ps.setInt(3, reservationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public Invoice retrieveInvoice(int reservationId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Invoice invoice = null;

        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(RETRIEVE_INVOICE_QUERY);
            ps.setInt(1, reservationId);
            rs = ps.executeQuery();
            if (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setReservationId(rs.getInt("reservation_id"));
                invoice.setAmount(rs.getDouble("amount"));
                invoice.setState(rs.getString("state"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return invoice;
    }

    @Override
    public void updateInvoice(int invoiceId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(UPDATE_INVOICE_QUERY);
            ps.setString(1, STATE_PAID);
            ps.setString(2, STATE_PAID);
            ps.setInt(3, invoiceId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }
}
