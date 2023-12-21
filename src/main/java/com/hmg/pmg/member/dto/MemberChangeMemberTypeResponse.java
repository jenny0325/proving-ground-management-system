package com.hmg.pmg.member.dto;

import com.hmg.pmg.member.entity.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberChangeMemberTypeResponse {

  private String email;
  private MemberType beforeType;
  private MemberType afterType;

  public MemberChangeMemberTypeResponse toResponse() {
    return new MemberChangeMemberTypeResponse(email, beforeType, afterType);
  }
}
