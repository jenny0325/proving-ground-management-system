package com.hmg.pmg.refresh_token;

import com.hmg.pmg.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String refreshTokenKey;
  private String refreshTokenValue;

  @Builder
  public RefreshToken(String refreshTokenKey, String refreshTokenValue) {
    this.refreshTokenKey = refreshTokenKey;
    this.refreshTokenValue = refreshTokenValue;
  }

  public void updateRefreshTokenValue(String refreshTokenValue) {
    this.refreshTokenValue = refreshTokenValue;
  }
}
