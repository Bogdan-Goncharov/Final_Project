package controller;

import model.PlayerStats;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import service.PlayerStatsService;

import java.util.Optional;

@RestController
@RequestMapping("/players/stats")
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
