package com.project.controller;

import com.project.model.PlayerStats;
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
        Optional<PlayerStats> stats = playerStatsService.findByUserId(userId);

        return stats.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{userId}")
    public ResponseEntity<PlayerStats> updatePlayerStats(
            @PathVariable Long userId,
            @RequestBody PlayerStats playerStatsDetails) {
        PlayerStats updatedStats = playerStatsService.updatePlayerStats(userId, playerStatsDetails);
        return ResponseEntity.ok(updatedStats);
    }

}
