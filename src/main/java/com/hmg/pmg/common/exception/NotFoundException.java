package com.hmg.pmg.common.exception;


import static com.hmg.pmg.common.response.ErrorMessage.ENTITY_NOT_FOUND_ERROR;

public class NotFoundException extends BaseException {
	public NotFoundException() {
		super(ENTITY_NOT_FOUND_ERROR);
	}

	public NotFoundException(String description) {
		super(description, ENTITY_NOT_FOUND_ERROR);
	}
}
