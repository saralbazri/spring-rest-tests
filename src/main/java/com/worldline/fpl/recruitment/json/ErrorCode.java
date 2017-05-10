package com.worldline.fpl.recruitment.json;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error code
 * 
 * @author A525125
 *
 */
@AllArgsConstructor
public enum ErrorCode {

	NOT_FOUND_ACCOUNT(HttpStatus.NOT_FOUND),
	NOT_FOUND_TRANSACTION(HttpStatus.NOT_FOUND),
	FORBIDDEN_TRANSACTION(HttpStatus.FORBIDDEN),
	INVALID_REQUEST(HttpStatus.BAD_REQUEST);
	

	@Getter
	private HttpStatus httpStatus;
}
