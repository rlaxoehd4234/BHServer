package com.BHServer.www.repository;

import com.BHServer.www.domain.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, String> {
    List<PlayerStats> findTop100ByOrderByPlayerKillsDesc();
    List<PlayerStats> findTop100ByOrderByDeathsDesc();
    List<PlayerStats> findTop100ByOrderByTotalPlayTimeDesc();
}
