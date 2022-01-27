package com.meli.loan.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to represent an errors.
 * @author diana.ciro
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {

	/**
	 * Indicate the error code status.
	 */
	private int code;
	
	/**
	 * Indicate the error message.
	 */
	private String message;
}
