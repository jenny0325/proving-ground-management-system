package com.hmg.pmg.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

  SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 시스템 에러가 발생했습니다."),
  UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
  ACCESS_DENIED_ERROR(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
  INVALID_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "요청한 값이 올바르지 않습니다."),
  VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효한 값이 아닙니다."),
  ENTITY_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 엔티티입니다."),
  ENTITY_DUPLICATE_ERROR(HttpStatus.CONFLICT, "중복되는 엔티티입니다."),
  UNABLE_CHANGE_MemberType(HttpStatus.BAD_REQUEST, "동일한 권한으로 변경할 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String description;
}
