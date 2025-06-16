package com.project.controller;

import com.project.model.PlayerStats;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;
import com.project.service.PlayerStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user/stats")
@RequiredArgsConstructor
@Tag(name = "Player Stats", description = "Operations related to player statistics")
public class PlayerStatsController {
    private final PlayerStatsService playerStatsService;

    @PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
    @GetMapping("/{userId}")
    @Operation(summary = "Get player stats", description = "Returns player statistics by user ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player stats found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Player stats not found")
    })
    public ResponseEntity<PlayerStats> getPlayerStats(@PathVariable Long userId) {
        Optional<PlayerStats> stats = playerStatsService.findByUserId(userId);

        return stats.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());


    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    @Operation(summary = "Update player stats", description = "Updates player statistics by user ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player stats updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Player stats not found")
    })
    public ResponseEntity<PlayerStats> updatePlayerStats(
            @PathVariable Long userId,
            @Valid @RequestBody PlayerStats playerStatsDetails) {
        PlayerStats updatedStats = playerStatsService.updatePlayerStats(userId, playerStatsDetails);
        return ResponseEntity.ok(updatedStats);
    }

}
