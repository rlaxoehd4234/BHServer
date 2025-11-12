package com.BHServer.www.service.impl;

import com.BHServer.www.domain.PlayerStats;
import com.BHServer.www.dto.PlayerStatsDto;
import com.BHServer.www.dto.PlayerStatsRankingResponseDto;
import com.BHServer.www.repository.PlayerStatsRepository;
import com.BHServer.www.service.PlayerStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PlayerStatsServiceV1 implements PlayerStatsService {

    private final PlayerStatsRepository playerStatsRepository;

    @Transactional(readOnly = true)
    @Override
    public PlayerStatsRankingResponseDto getRankingByCategory(String category) {
        List<PlayerStats> playerList;
        switch (category) {
            case "kills" -> playerList = playerStatsRepository.findTop100ByOrderByPlayerKillsDesc();
            case "playTime" -> playerList = playerStatsRepository.findTop100ByOrderByTotalPlayTimeDesc();
            case "deaths" -> playerList = playerStatsRepository.findTop100ByOrderByDeathsDesc();
            default -> throw new IllegalStateException("not valid category");
        };

        AtomicInteger rankCounter = new AtomicInteger(1);

        List<PlayerStatsDto> rankList = playerList.stream().
                map(e -> PlayerStatsDto.from(e, category, rankCounter.getAndIncrement()))
                .toList();

        return PlayerStatsRankingResponseDto.of(category, rankList);
    }
}
