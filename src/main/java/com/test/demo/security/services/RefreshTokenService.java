package com.test.demo.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.security.jwt.exception.TokenRefreshException;
import com.test.demo.models.RefreshToken;
import com.test.demo.repository.RefreshTokenRepository;
import com.test.demo.repository.UserRepository;

@Service
public class RefreshTokenService {
  @Value("${demo.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Long userId) {
	RefreshToken refreshToken = new RefreshToken();
	refreshToken.setUser(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
   
    if (refreshTokenRepository.findByUserId(userId).isEmpty()) {
    
    	refreshToken.setToken(UUID.randomUUID().toString());
    	refreshToken = refreshTokenRepository.save(refreshToken);
    }else {
    	refreshToken = refreshTokenRepository.findByUserId(userId).get();
    	refreshToken.setToken(UUID.randomUUID().toString());
    	refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    	refreshToken = refreshTokenRepository.save(refreshToken);
    }

  
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}