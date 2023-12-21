package com.hmg.pmg.auth.service;


import com.hmg.pmg.auth.dto.AuthLoginDto;
import com.hmg.pmg.auth.dto.TokenDto;
import com.hmg.pmg.auth.dto.TokenRequestDto;
import com.hmg.pmg.common.exception.UnauthorizedException;
import com.hmg.pmg.config.jwt.TokenProvider;
import com.hmg.pmg.refresh_token.RefreshToken;
import com.hmg.pmg.refresh_token.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final TokenProvider tokenProvider;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public TokenDto login(AuthLoginDto memberLoginDto) {
    UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();
    Authentication authentication = authenticationManagerBuilder.getObject()
        .authenticate(authenticationToken);

    TokenDto tokenDto = tokenProvider.generateToken(authentication);
    refreshTokenRepository.findByRefreshTokenKey(
            authentication.getName())
        .ifPresent(refreshToken -> refreshTokenRepository.deleteById(refreshToken.getId()));

    refreshTokenRepository.save(
        memberLoginDto.toEntity(authentication.getName(), tokenDto.getRefreshToken()));

    return tokenDto;
  }

  @Override
  public void logout(TokenRequestDto tokenRequestDto) {

    Authentication authentication = getAuthentication(tokenRequestDto);
    RefreshToken refreshToken = getRefreshToken(tokenRequestDto.getRefreshToken(), authentication);

    refreshTokenRepository.deleteById(refreshToken.getId());
  }

  @Override
  public TokenDto refresh(TokenRequestDto tokenRequestDto) {
    return null;
  }


  private RefreshToken getRefreshToken(String requestRefreshToken, Authentication authentication) {
    RefreshToken refreshToken = refreshTokenRepository.findByRefreshTokenKey(
            authentication.getName())
        .orElseThrow(() -> new UnauthorizedException("로그아웃한 사용자입니다."));

    if (!StringUtils.equals(refreshToken.getRefreshTokenValue(), requestRefreshToken)) {
      throw new UnauthorizedException("토큰의 유저 정보가 일치하지 않습니다.");
    }

    return refreshToken;
  }

  private Authentication getAuthentication(TokenRequestDto tokenRequestDto) {
    if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
      throw new ValidationException("Refresh Token이 유효하지 않습니다.");
    }

    return tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
  }

}