package by.it.hotel.dao;

import java.io.Serializable;

public class DaoException extends Exception implements Serializable {

    private static final long serialVersionUID = 499782180222728743L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
