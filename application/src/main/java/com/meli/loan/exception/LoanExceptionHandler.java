package com.meli.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.meli.loan.response.ResponseError;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to handle exceptions.
 * @author diana.ciro
 *
 */
@ControllerAdvice
@Slf4j
public class LoanExceptionHandler {

	private static final String TROWABLE_EXCEPTION = "Internal server error";

	/**
	 * Handles Business exceptions.
	 * @param businessException is the exception to handle.
	 * @return ResponseEntity<ResponseError> instance with a custom error. 
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseError> manageBusinessExceptionHandler(BusinessException businessException){
		log.warn(businessException.getMessage());
		ResponseError response = new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), businessException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	/**
	 * Handles general exceptions.
	 * @param throwable  is the exception to handle.
	 * @return ResponseEntity<ResponseError> instance with a custom error. . 
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ResponseError> manageGeneralException(Throwable throwable) {
		log.error(TROWABLE_EXCEPTION, throwable.getMessage());
		ResponseError response = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), TROWABLE_EXCEPTION);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
