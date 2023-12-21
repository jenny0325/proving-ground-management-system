package com.hmg.pmg.auth.service;


import com.hmg.pmg.auth.dto.AuthLoginDto;
import com.hmg.pmg.auth.dto.TokenDto;
import com.hmg.pmg.auth.dto.TokenRequestDto;

public interface AuthService {

	TokenDto login(AuthLoginDto memberLoginDto);

	void logout(TokenRequestDto tokenRequestDto);

	TokenDto refresh(TokenRequestDto tokenRequestDto);
}
