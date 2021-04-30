package by.it.hotel.service.validation;

import by.it.hotel.service.ServiceException;

import java.io.Serializable;

public class ValidationException extends ServiceException implements Serializable {
    private static final long serialVersionUID = 7162892837856319120L;

    public ValidationException() {
        super();
    }

}
