package com.hmg.pmg.member.dto;

import com.hmg.pmg.member.entity.Member;
import com.hmg.pmg.member.entity.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpResponse {

  private String email;
  private String password;
  private String name;
  private String company;
  private String team;
  private String position;
  private String phoneNumber;
  private MemberType memberType;

  public MemberSignUpResponse of(Member member) {
    return MemberSignUpResponse.builder()
        .email(member.getEmail())
        .password(member.getPassword())
        .name(member.getName())
        .company(member.getCompany())
        .team(member.getTeam())
        .position(member.getPosition())
        .phoneNumber(member.getPhoneNumber())
        .memberType(MemberType.ROLE_USER)
        .build();
  }

}
