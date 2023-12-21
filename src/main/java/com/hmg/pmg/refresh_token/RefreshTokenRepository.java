package com.hmg.pmg.refresh_token;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByRefreshTokenKey(String refreshTokenKey);
  Optional<RefreshToken> findById(Long tokenId);
  void deleteById(Long tokenId);
}
