package com.meli.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.meli.loan.response.ResponseError;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;

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
	 * Handles ConstraintViolationException.
	 * @param constraintViolationException is the exception to handle.
	 * @return ResponseEntity<ResponseError> instance with a custom error.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseError> manageConstraintViolationException(ConstraintViolationException constraintViolationException){
		log.warn(constraintViolationException.getMessage());
		ResponseError response = new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), constraintViolationException.getConstraintViolations().iterator().next().getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Handles NotFoundException.
	 * @param notFoundException is the exception to handle.
	 * @return ResponseEntity<ResponseError> instance with a custom error.
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseError> manageNotFoundException(NotFoundException notFoundException){
		log.warn(notFoundException.getMessage());
		ResponseError response = new ResponseError(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles general exceptions.
	 * @param throwable  is the exception to handle.
	 * @return ResponseEntity<ResponseError> instance with a custom error. .
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ResponseError> manageGeneralException(Throwable throwable) {
		log.error(TROWABLE_EXCEPTION, throwable.getMessage());
		System.err.println(throwable.getMessage());
		ResponseError response = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), TROWABLE_EXCEPTION);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
