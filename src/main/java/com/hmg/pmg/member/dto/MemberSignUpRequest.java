package com.hmg.pmg.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberSignUpRequest {

    @NotBlank
    @Email(regexp = "^[A-Za-z][A-Za-z0-9.-_]+@[A-Za-z0-9.-_]+.[A-Za-z]+",
        message = "이메일 형식에 맞지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호 값이 없습니다.")
    @NotBlank
    private String password;

    @NotBlank(message = "이름 값이 없습니다.")
    private String name;

    @NotBlank(message = "휴대전화 번호 값이 없습니다.")
    private String phoneNumber;

    @NotBlank(message = "소속회사 값이 없습니다.")
    private String company;

    @NotBlank(message = "소속팀 값이 없습니다.")
    private String team;

    @NotBlank(message = "직책 값이 없습니다.")
    private String position;

}
