package com.BHServer.www.dto;

import java.util.List;

public record PlayerStatsRankingResponseDto(
        String category,
        List<PlayerStatsDto> rankList
) {
    public static PlayerStatsRankingResponseDto of(String category, List<PlayerStatsDto> rankList){
        return new PlayerStatsRankingResponseDto(category, rankList);
    }
}
