package com.hmg.pmg.member.controller;

import com.hmg.pmg.common.response.CustomResponse;
import com.hmg.pmg.common.response.SuccessMessage;
import com.hmg.pmg.member.dto.ChangeMemberTypeRequest;
import com.hmg.pmg.member.dto.ChangeMemberTypeResponse;
import com.hmg.pmg.member.dto.MemberSignUpRequest;
import com.hmg.pmg.member.dto.MemberSignUpResponse;
import com.hmg.pmg.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/signup")
  public ResponseEntity<CustomResponse<MemberSignUpResponse>> signUp(
      @RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {

    CustomResponse<MemberSignUpResponse> response = CustomResponse
        .success(memberService.signUp(memberSignUpRequest), SuccessMessage.SIGNUP_SUCCESS);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/type")
  @PreAuthorize(value = "hasRole('ADMIN')")
  public ResponseEntity<CustomResponse<ChangeMemberTypeResponse>> changeMemberType(
      @RequestBody @Valid ChangeMemberTypeRequest request) {

    CustomResponse<ChangeMemberTypeResponse> response = CustomResponse.success(
        memberService.changeMemberType(request.getEmail(), request.getAfterGrade()),
        SuccessMessage.CHANGE_MEMBER_TYPE_SUCCESS);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
