package com.BHServer.www.controller;

import com.BHServer.www.config.JwtTokenProvider;
import com.BHServer.www.dto.RefreshRequest;
import com.BHServer.www.dto.RefreshResponse;
import com.BHServer.www.repository.RefreshTokenRepository;
import com.BHServer.www.service.impl.RefreshTokenService;
import com.BHServer.www.service.impl.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RefreshController {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    // ==========================================
    // 🔥 Access Token 재발급
    // ==========================================
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {

        String refreshToken = request.refreshToken();

        // 1) 토큰 형식부터 유효성 검사 (서명, 만료)
        if (!refreshTokenService.validate(refreshToken)) {
            return ResponseEntity.status(401).body("Invalid or expired refresh token");
        }

        // 2) 토큰에서 username 추출
        String username = jwtTokenProvider.getUsername(refreshToken);

        // 3) DB에 저장된 refresh token과 요청한 토큰이 같은지 검증
        return refreshTokenRepository.findByUsername(username)
                .map(savedToken -> {

                    if (!savedToken.getToken().equals(refreshToken)) {
                        return ResponseEntity.status(401).body("Refresh token mismatch");
                    }

                    // 4) 새로운 Access Token 생성
                    String newAccessToken = jwtTokenProvider.createAccessToken(username, userDetailsService.loadUserByUsername(username).getAuthorities());

                    // 5) Refresh 만료가 임박했다면 Refresh Token도 새로 주는 버전
                    long now = System.currentTimeMillis();
                    long remain = savedToken.getExpiry() - now;

                    String newRefreshToken = null;

                    // 남은 시간이 3일 이하라면 새 Refresh 발급
                    if (remain < (1000L * 60 * 60 * 24 * 3)) {
                        newRefreshToken = jwtTokenProvider.createRefreshToken(username);
                        refreshTokenService.saveOrUpdate(username, newRefreshToken);
                    }

                    // 6) 응답 생성
                    return ResponseEntity.ok(
                            new RefreshResponse(newAccessToken, newRefreshToken)
                    );
                })
                .orElse(ResponseEntity.status(401).body("Refresh token not found"));
    }
}