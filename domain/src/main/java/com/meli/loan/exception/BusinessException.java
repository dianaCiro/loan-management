package com.meli.loan.exception;

/**
 * This class is used to throw a business exception.
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/**
	 * Business exception constructor.
	 * @param message to describe the exception generated.
	 */
	public BusinessException(String message) {
		super(message);
	}
}
