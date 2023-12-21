package com.hmg.pmg.auth.dto;

import com.hmg.pmg.refresh_token.RefreshToken;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@AllArgsConstructor
public class AuthLoginDto {

  @NotEmpty
  @Email(message = "email 형식과 일치해야 합니다.")
  private String email;

  @NotEmpty
  private String password;

  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }

  public RefreshToken toEntity(String name, String token) {
    return RefreshToken.builder()
        .refreshTokenKey(name)
        .refreshTokenValue(token)
        .build();
  }
}
