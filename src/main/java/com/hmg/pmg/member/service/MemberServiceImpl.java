package com.hmg.pmg.member.service;

import com.hmg.pmg.common.exception.DuplicateException;
import com.hmg.pmg.common.exception.NotFoundException;
import com.hmg.pmg.member.dto.ChangeMemberTypeResponse;
import com.hmg.pmg.member.entity.Member;
import com.hmg.pmg.member.entity.MemberType;
import com.hmg.pmg.member.dto.MemberSignUpRequest;
import com.hmg.pmg.member.dto.MemberSignUpResponse;
import com.hmg.pmg.member.repository.MemberRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Validated
@Slf4j
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  @Transactional
  public MemberSignUpResponse signUp(@Validated MemberSignUpRequest memberSignUpRequest)
      throws DuplicateException {

    memberRepository.findByEmail(memberSignUpRequest.getEmail()).ifPresent(m -> {
      throw new DuplicateException();
    });

    Member member = Member.builder()
        .email(memberSignUpRequest.getEmail())
        .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
        .company(memberSignUpRequest.getCompany())
        .name(memberSignUpRequest.getName())
        .team(memberSignUpRequest.getTeam())
        .phoneNumber(memberSignUpRequest.getPhoneNumber())
        .position(memberSignUpRequest.getPosition())
        .memberType(MemberType.ROLE_USER)
        .build();

    memberRepository.save(member);

    return new MemberSignUpResponse().of(member);
  }

  @Override
  @Transactional(readOnly = true)
  public MemberSignUpResponse getMember(Long memberId) throws NotFoundException {
    Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
    return new MemberSignUpResponse().of(member);
  }

  @Override
  @Transactional
  public ChangeMemberTypeResponse changeMemberType(@NotNull String email, @NotNull MemberType memberType)
  {
    Member member = memberRepository.findByEmail(email).orElseThrow(NotFoundException::new);
    return member.changeMemberType(memberType);
  }
}
