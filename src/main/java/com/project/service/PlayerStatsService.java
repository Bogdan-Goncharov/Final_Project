package com.project.service;

import lombok.RequiredArgsConstructor;
import com.project.model.PlayerStats;
import com.project.model.User;
import org.springframework.stereotype.Service;
import com.project.repository.PlayerStatsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerStatsService {
    private final PlayerStatsRepository playerStatsRepository;

    public Optional<PlayerStats> findByUser(User user) {
        return playerStatsRepository.findByUser(user);
    }

    public PlayerStats savePlayerStats(PlayerStats playerStats) {
        return playerStatsRepository.save(playerStats);
    }
}
