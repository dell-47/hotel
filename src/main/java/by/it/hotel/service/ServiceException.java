package by.it.hotel.service;

import java.io.Serializable;

public class ServiceException extends Exception implements Serializable {

    private static final long serialVersionUID = 8419130523510612532L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
