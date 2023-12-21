package com.hmg.pmg.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hmg.pmg.common.exception.DuplicateException;
import com.hmg.pmg.common.exception.UnalbeChangeMemberTypeException;
import com.hmg.pmg.member.dto.ChangeMemberTypeResponse;
import com.hmg.pmg.member.dto.MemberSignUpRequest;
import com.hmg.pmg.member.dto.MemberSignUpResponse;
import com.hmg.pmg.member.entity.Member;
import com.hmg.pmg.member.entity.MemberType;
import com.hmg.pmg.member.repository.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  Member member;
  Member adminMember;
  MemberSignUpRequest signUpDto;

  @Mock
  MemberRepository memberRepository;

  @Spy
  PasswordEncoder passwordEncoder;

  @InjectMocks
  MemberServiceImpl memberService;

  @BeforeEach
  void init() {
    signUpDto = MemberSignUpRequest.builder()
        .email("example@test.com")
        .password("123456")
        .company("현대오토에버")
        .name("홍길동")
        .phoneNumber("010-0000-0000")
        .position("사원")
        .team("인사팀")
        .build();
    member = Member.builder()
        .memberType(MemberType.ROLE_USER)
        .email("example@test.com")
        .password("123456")
        .company("현대오토에버")
        .name("홍길동")
        .phoneNumber("010-0000-0000")
        .position("사원")
        .team("인사팀")
        .build();
    adminMember = Member.builder()
        .email("admin@test.com")
        .password("12341234")
        .memberType(MemberType.ROLE_ADMIN)
        .company("예시")
        .name("홍길동")
        .phoneNumber("010-0000-0000")
        .position("사원")
        .team("인사팀")
        .build();
  }

  @Test
  @DisplayName("회원가입 - 성공")
  public void signUpTestWhenSuccess() {
    when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.empty());

    MemberSignUpResponse memberSignUpResponse = memberService.signUp(signUpDto);

    Assertions.assertNotNull(memberSignUpResponse);
  }

  @Test
  @DisplayName("회원가입 - 실패 : 이미 동일한 이메일이 있을 경우")
  void duplicateEmailException() {

    when(memberRepository.findByEmail("example@test.com")).thenReturn(Optional.of(member));

    Assertions.assertThrows(DuplicateException.class, () -> memberService.signUp(signUpDto));

    verify(memberRepository).findByEmail(member.getEmail());
  }

  @Test
  @DisplayName("회원 권한 변경 - 성공")
  void changeMemberTypeSuccess() {

    // when
    when(memberRepository.findByEmail(adminMember.getEmail())).thenReturn(Optional.of(adminMember));

    ChangeMemberTypeResponse changeMemberTypeResponse = memberService.changeMemberType(
        adminMember.getEmail(), MemberType.ROLE_USER);

    // then
    assertAll(
        () -> assertThat(changeMemberTypeResponse.getEmail()).isEqualTo("admin@test.com"),
        () -> assertThat(changeMemberTypeResponse.getBeforeMemberType()).isEqualTo(
            MemberType.ROLE_ADMIN),
        () -> assertThat(changeMemberTypeResponse.getAfterMemberType()).isEqualTo(
            MemberType.ROLE_USER)
    );
  }

  @Test
  @DisplayName("회원 권한 변경 - 실패 : 같은 권한으로 변경")
  void changeMemberTypeFail() {

    when(memberRepository.findByEmail(adminMember.getEmail())).thenReturn(Optional.of(adminMember));

    Assertions.assertThrows(UnalbeChangeMemberTypeException.class,
        () -> memberService.changeMemberType(
            adminMember.getEmail(), MemberType.ROLE_ADMIN));

  }

}
