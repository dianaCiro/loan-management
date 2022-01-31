package com.meli.loan.exception;

/**
 * This class is used to throw a NotFoundException exception.
 */
public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * NotFoundException constructor.
     * @param message to describe the exception generated.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
