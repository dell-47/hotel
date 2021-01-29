package by.it.hotel.dao;

import by.it.hotel.entity.Apart;
import by.it.hotel.entity.ApartType;
import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

/**
 * This is an interface for hotel dao layer.
 */
public interface HotelDao {

    /**
     * This method is used to create a reservation.
     * @param reservation This entity is used when creating a reservation.
     * @throws DaoException Throws if database operation fails.
     */
    void createReservation(Reservation reservation) throws DaoException;


    /**
     * This method is used to update the reservation state.
     * @param reservationId The id of reservation to update.
     * @param state State for updating reservation.
     * @throws DaoException Throws if database operation fails.
     */
    void updateReservation(int reservationId, String state) throws DaoException;


    /**
     * This method is used to update the apartment in the reservation.
     * @param reservationId The id of reservation to update.
     * @param apartId The id of apartment to update.
     * @throws DaoException Throws if database operation fails.
     */
    void updateReservation(int reservationId, int apartId) throws DaoException;


    /**
     * This method is used to retrieve the apartment type by id.
     * @param id Apartment type id for retrieving.
     * @return Returns the required apartment type.
     * @throws DaoException Throws if database operation fails.
     */
    ApartType retrieveApartType(int id) throws DaoException;


    /**
     * This method is used to retrieve information required for invoicing.
     * @param reservationId The id of required reservation.
     * @return Returns the required invoice.
     * @throws DaoException Throws if database operation fails.
     */
    Invoice retrieveInvoice(int reservationId) throws DaoException;


    /**
     * This method is used to search available apartments for the specified dates and apartment type.
     * @param id The id of required apartment type.
     * @param inDate Specified check-in date.
     * @param outDate Specified check-out date.
     * @return Returns a list of available apartments.
     * @throws DaoException Throws if database operation fails.
     */
    List<Apart> searchAparts(int id, LocalDate inDate, LocalDate outDate) throws DaoException;


    /**
     * This method is used to search available apartment types for the specified dates.
     * @param inDate Specified check-in date.
     * @param outDate Specified check-out date.
     * @param locale Used to specify required description language.
     * @return Returns a list of available apartment types.
     * @throws DaoException Throws if database operation fails.
     */
    List<ApartType> searchApartTypes(LocalDate inDate, LocalDate outDate, String locale) throws DaoException;


    /**
     * This method is used to retrieve all apartment types.
     * @param locale Used to specify required description language.
     * @return Returns a list of all apartment types.
     * @throws DaoException Throws if database operation fails.
     */
    List<ApartType> retrieveAllApartTypes(String locale) throws DaoException;


    /**
     * This method is used to search reservations.
     * @return Returns a list of reservations.
     * @throws DaoException Throws if database operation fails.
     */
    List<Reservation> searchReservations() throws DaoException;


    /**
     * This method is used to search user's reservations.
     * @param userId User id.
     * @return Returns a list of user's reservations.
     * @throws DaoException Throws if database operation fails.
     */
    List<Reservation> searchReservations(int userId) throws DaoException;
}
