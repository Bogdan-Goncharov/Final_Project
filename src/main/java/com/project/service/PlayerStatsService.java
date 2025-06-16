package com.project.service;

import lombok.RequiredArgsConstructor;
import com.project.model.PlayerStats;
import org.springframework.stereotype.Service;
import com.project.repository.PlayerStatsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerStatsService {
    private final PlayerStatsRepository playerStatsRepository;

    public PlayerStats updatePlayerStats(Long userId, PlayerStats playerStatsDetails) {
        PlayerStats playerStats = playerStatsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("PlayerStats not found for user_id: " + userId));
        playerStats.setTotalPoints(playerStatsDetails.getTotalPoints());
        playerStats.setTotalAchievements(playerStatsDetails.getTotalAchievements());
        playerStats.setRanking(playerStatsDetails.getRanking());
        return playerStatsRepository.save(playerStats);
    }

    public Optional<PlayerStats> findByUserId(Long userId) {
        return playerStatsRepository.findByUserId(userId);
    }

}
