package com.BHServer.www.controller;

import com.BHServer.www.dto.PlayerStatsRankingResponseDto;
import com.BHServer.www.service.PlayerStatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
@Tag(name = "플레이어 랭킹 API", description = "플레이어의 랭킹 및 통계 엔드포인트")
public class PlayerStatsController {
    private final PlayerStatsService playerStatsService;

    @Operation(summary = "카테고리별 랭킹 조회", description = "kills, playTime, deaths 의 카테고리 별 플레이어 랭킹을 반환합니다.")
    @GetMapping("/ranking/{category}")
    public ResponseEntity<PlayerStatsRankingResponseDto> getRanking(@PathVariable String category){
        return ResponseEntity.ok(playerStatsService.getRankingByCategory(category));
    }
}
