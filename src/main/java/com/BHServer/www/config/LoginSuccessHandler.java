package com.BHServer.www.config;

import com.BHServer.www.service.impl.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = jwtTokenProvider.createAccessToken(
                authentication.getName(),
                authentication.getAuthorities()
        );
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication.getName());

        refreshTokenService.saveOrUpdate(authentication.getName(),refreshToken);

        response.setContentType("application/json;charset=UTF-8");

        Map<String, Object> responseBody = Map.of(
                "accessToken" , accessToken,
                "refreshToken", refreshToken,
                "username", authentication.getName()
        );

        new ObjectMapper().writeValue(response.getWriter(), responseBody);

    }
}
