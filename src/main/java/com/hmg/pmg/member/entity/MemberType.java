package com.hmg.pmg.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {

  ROLE_ADMIN("ADMIN"),
  ROLE_USER("USER");

  private final String value;
}
