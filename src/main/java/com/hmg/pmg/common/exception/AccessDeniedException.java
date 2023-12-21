package com.hmg.pmg.common.exception;


import static com.hmg.pmg.common.response.ErrorMessage.ACCESS_DENIED_ERROR;

public class AccessDeniedException extends BaseException {

	public AccessDeniedException() {
		super(ACCESS_DENIED_ERROR);
	}

	public AccessDeniedException(String description) {
		super(description, ACCESS_DENIED_ERROR);
	}
}
