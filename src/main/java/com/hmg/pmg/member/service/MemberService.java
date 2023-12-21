package com.hmg.pmg.member.service;

import com.hmg.pmg.member.dto.ChangeMemberTypeResponse;
import com.hmg.pmg.member.dto.MemberSignUpRequest;
import com.hmg.pmg.member.dto.MemberSignUpResponse;
import com.hmg.pmg.member.entity.MemberType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface MemberService {

  MemberSignUpResponse signUp(MemberSignUpRequest memberSignUpRequest);

  MemberSignUpResponse getMember(Long memberId) throws NotFoundException;

  ChangeMemberTypeResponse changeMemberType(@NotNull String email, @NotNull MemberType memberType);

}
