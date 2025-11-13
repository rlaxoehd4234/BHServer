package com.BHServer.www.service.impl;

import com.BHServer.www.config.JwtTokenProvider;
import com.BHServer.www.domain.RefreshToken;
import com.BHServer.www.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void saveOrUpdate(String username, String refreshToken){

        Long expiry = jwtTokenProvider.getRefreshValidity();

        refreshTokenRepository.findByUsername(username)
                .ifPresentOrElse(
                        (exist) -> exist.updateToken(refreshToken, expiry),
                        () -> refreshTokenRepository.save(
                                RefreshToken.builder()
                                        .username(username)
                                        .token(refreshToken)
                                        .expiry(expiry)
                                        .build()
                        )
                );
    }

    public boolean validate(String refreshToken){
        return jwtTokenProvider.validateToken(refreshToken);    }
}
