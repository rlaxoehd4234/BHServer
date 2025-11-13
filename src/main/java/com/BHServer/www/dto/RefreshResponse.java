package com.BHServer.www.dto;

public record RefreshResponse(
        String newAccessToken,
        String newRefreshToken
) {
}
