package com.BHServer.www.dto;

import com.BHServer.www.domain.PlayerStats;

public record PlayerStatsDto(
        int rank,
        String playerName,
        long scoreValue
) {
    public static PlayerStatsDto from(PlayerStats playerStats, String category, int rank){
        long score = getScore(playerStats, category);
        return new PlayerStatsDto(rank, playerStats.getPlayerName(), score);
    }

    public static long getScore(PlayerStats playerStats, String category){
        return switch (category){
            case "kills" -> playerStats.getPlayerKills();
            case "playTime" -> playerStats.getTotalPlayTime();
            case "deaths" -> playerStats.getDeaths();
            default -> 0L;
        };
    }
}
