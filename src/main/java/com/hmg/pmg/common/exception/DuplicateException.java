package com.hmg.pmg.common.exception;

import static com.hmg.pmg.common.response.ErrorMessage.ENTITY_DUPLICATE_ERROR;

public class DuplicateException extends BaseException {

	public DuplicateException() {
		super(ENTITY_DUPLICATE_ERROR);
	}

	public DuplicateException(String description) {
		super(description, ENTITY_DUPLICATE_ERROR);
	}
}
