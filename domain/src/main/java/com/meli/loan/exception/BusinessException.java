package com.meli.loan.exception;

/**
 * This class is used to throw a business exception.
 * @author diana.ciro
 *
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
	
	/**
	 * Business exception constructor.
	 * @param message to describe the exception generated.
	 * @param cause contains the exception trace.
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
