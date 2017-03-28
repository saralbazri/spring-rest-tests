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

	INVALID_ACCOUNT(HttpStatus.NOT_FOUND),
	INVALID_TRANSACTION(HttpStatus.FORBIDDEN);

	@Getter
	private HttpStatus httpStatus;
}
