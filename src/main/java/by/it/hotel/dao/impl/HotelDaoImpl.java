package by.it.hotel.dao.impl;

import by.it.hotel.dao.DaoException;
import by.it.hotel.dao.HotelDao;
import by.it.hotel.dao.pool.ConnectionPool;
import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.it.hotel.dao.impl.DaoConstants.*;

public class HotelDaoImpl implements HotelDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

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
            ps.setBigDecimal(6, reservation.getSubtotalPrice());
            ps.setBigDecimal(7, reservation.getTaxes());
            ps.setBigDecimal(8, reservation.getTotalPrice());
            ps.setTimestamp(9, java.sql.Timestamp.valueOf(LocalDateTime.now().withNano(0)));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public List<ApartType> searchApartTypes(LocalDate inDate, LocalDate outDate, String locale) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ApartType> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(SEARCH_APART_TYPES_QUERY);

            ps.setString(1, STATE_PROCESSING);
            ps.setString(2, STATE_CANCELED);
            ps.setString(3, STATE_DECLINED);
            ps.setDate(4, java.sql.Date.valueOf(inDate));
            ps.setDate(5, java.sql.Date.valueOf(outDate));
            ps.setDate(6, java.sql.Date.valueOf(inDate));
            ps.setDate(7, java.sql.Date.valueOf(outDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                ApartType type = new ApartType();
                type.setId(rs.getInt("id"));
                type.setPrice(rs.getDouble("price"));
                type.setType(rs.getString("type"));
                type.setDescription(rs.getString("description_" + locale));
                type.setImage(rs.getString("image"));
                list.add(type);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<ApartType> retrieveAllApartTypes(String locale) throws DaoException {
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
                type.setDescription(rs.getString("description_" + locale));
                type.setImage(rs.getString("image"));
                list.add(type);
            }
        } catch (SQLException e) {
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
                apartType.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return apartType;
    }

    @Override
    public List<Reservation> searchReservations(int userId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reservation> list = new ArrayList<>();
        int num = 1;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(SEARCH_RESERVATIONS_BY_ID_QUERY);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setNum(num++);
                reservation.setInDate(rs.getDate("in_date").toLocalDate());
                reservation.setOutDate(rs.getDate("out_date").toLocalDate());
                reservation.setState(rs.getString("state"));
                reservation.setSubtotalPrice(rs.getBigDecimal("subtotal_price"));
                reservation.setTaxes(rs.getBigDecimal("taxes"));
                reservation.setTotalPrice(rs.getBigDecimal("total_price"));
                list.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<Reservation> searchReservations() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reservation> list = new ArrayList<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(SEARCH_RESERVATIONS_QUERY);
            ps.setString(1, STATE_PROCESSING);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setInDate(rs.getDate("in_date").toLocalDate());
                reservation.setOutDate(rs.getDate("out_date").toLocalDate());
                reservation.setApartTypeId(rs.getInt("apart_type"));
                reservation.setApartType(rs.getString("type"));
                reservation.setSubtotalPrice(rs.getBigDecimal("subtotal_price"));
                reservation.setTaxes(rs.getBigDecimal("taxes"));
                reservation.setTotalPrice(rs.getBigDecimal("total_price"));
                reservation.setTime(rs.getTimestamp("time").toLocalDateTime());
                list.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
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
            ps.setString(2, STATE_PROCESSING);
            ps.setString(3, STATE_CANCELED);
            ps.setString(4, STATE_DECLINED);
            ps.setDate(5, java.sql.Date.valueOf(inDate));
            ps.setDate(6, java.sql.Date.valueOf(outDate));
            ps.setDate(7, java.sql.Date.valueOf(inDate));
            ps.setDate(8, java.sql.Date.valueOf(outDate));
            rs = ps.executeQuery();
            while (rs.next()) {
                Apart apart = new Apart();
                apart.setId(rs.getInt("id"));
                apart.setNum(rs.getInt("num"));
                list.add(apart);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return list;
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
                invoice.setInDate(rs.getDate("in_date").toLocalDate());
                invoice.setOutDate(rs.getDate("out_date").toLocalDate());
                invoice.setApartType(rs.getString("type"));
                invoice.setCost(rs.getBigDecimal("price"));
                invoice.setSubtotalPrice(rs.getBigDecimal("subtotal_price"));
                invoice.setTaxes(rs.getBigDecimal("taxes"));
                invoice.setTotalPrice(rs.getBigDecimal("total_price"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return invoice;
    }

    @Override
    public void updateReservation(int reservationId, String state) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(UPDATE_RESERVATION_STATE_QUERY);
            ps.setString(1, state);
            ps.setInt(2, reservationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }
}