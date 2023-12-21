package com.hmg.pmg.common.exception;

import static com.hmg.pmg.common.response.ErrorMessage.UNABLE_CHANGE_MemberType;


public class UnalbeChangeMemberTypeException extends BaseException {

  public UnalbeChangeMemberTypeException() {
    super(UNABLE_CHANGE_MemberType);
  }

  public UnalbeChangeMemberTypeException(String description) {
    super(description, UNABLE_CHANGE_MemberType);
  }

}
