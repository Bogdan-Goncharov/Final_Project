package com.project.controller;

import com.project.model.PlayerStats;
import com.project.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.project.service.PlayerStatsService;

import java.util.Optional;

@RestController
@RequestMapping("/player/stats")
@RequiredArgsConstructor
public class PlayerStatsController {
    private final PlayerStatsService playerStatsService;

    @GetMapping("/{userId}")
    public ResponseEntity<PlayerStats> getPlayerStats(@PathVariable Long userId) {
        Optional<PlayerStats> stats = playerStatsService.findByUser(new User(userId, "", "", ""));
        return stats.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
