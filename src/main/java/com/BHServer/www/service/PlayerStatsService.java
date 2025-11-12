package com.BHServer.www.service;

import com.BHServer.www.domain.PlayerStats;
import com.BHServer.www.dto.PlayerStatsRankingResponseDto;

import java.util.List;

public interface PlayerStatsService {
    PlayerStatsRankingResponseDto getRankingByCategory(String category);
}
