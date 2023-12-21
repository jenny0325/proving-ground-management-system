package com.hmg.pmg.member.dto;

import com.hmg.pmg.member.entity.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ChangeMemberTypeResponse {

  private String email;
  private MemberType beforeMemberType;
  private MemberType afterMemberType;

}
