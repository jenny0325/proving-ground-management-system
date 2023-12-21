package com.hmg.pmg.member.entity;

import com.hmg.pmg.common.AbstractEntity;
import com.hmg.pmg.common.exception.UnalbeChangeMemberTypeException;
import com.hmg.pmg.member.dto.ChangeMemberTypeResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Table(name = "MEMBER")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "COMPANY", nullable = false)
  private String company;

  @Column(name = "TEAM", nullable = false)
  private String team;

  @Column(name = "POSITION", nullable = false)
  private String position;

  @Column(name = "PHONENUMBER", nullable = false)
  private String phoneNumber;

  @Column(name = "MEMBER_TYPE", nullable = false)
  @Enumerated(EnumType.STRING)
  private MemberType memberType;

  public ChangeMemberTypeResponse changeMemberType(MemberType afterMemberType) {
    if (memberType.equals(afterMemberType)) {
      throw new UnalbeChangeMemberTypeException();
    }
    final MemberType beforeMemberType = this.memberType;

    this.memberType = afterMemberType;
    return new ChangeMemberTypeResponse(email, beforeMemberType, afterMemberType);
  }

}

