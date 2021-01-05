package by.it.hotel.dao.pool;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException() {}

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
