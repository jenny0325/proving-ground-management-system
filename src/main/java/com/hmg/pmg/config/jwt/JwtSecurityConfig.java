package com.hmg.pmg.config.jwt;

import com.hmg.pmg.refresh_token.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
public class JwtSecurityConfig extends
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final TokenProvider tokenProvider;
  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    JwtFilter jwtFilter = new JwtFilter(tokenProvider, refreshTokenRepository);
    builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}