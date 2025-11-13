package com.BHServer.www.dto;

import lombok.Getter;

public record RefreshRequest(
        String refreshToken
) {
}
