package com.hmg.pmg.member.dto;

import com.hmg.pmg.member.entity.MemberType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMemberTypeRequest {


    @NotEmpty(message = "이메일 값이 없습니다.")
    @Email()
    private String email;

    @NotNull(message = "바꿀 등급이 필요합니다.")
    private MemberType afterGrade;


}
