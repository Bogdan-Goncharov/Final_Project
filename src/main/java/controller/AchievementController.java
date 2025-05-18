package controller;

import model.Achievement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import service.AchievementService;

import java.util.List;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;

    @GetMapping("/")
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }

    @PostMapping("/")
    public ResponseEntity<Achievement> addAchievement(@RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.saveAchievement(achievement));
    }
}
