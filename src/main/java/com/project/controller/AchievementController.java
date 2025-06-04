package com.project.controller;

import com.project.model.Achievement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.project.service.AchievementService;

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

    @PostMapping
    public ResponseEntity<Achievement> addAchievement(@Valid @RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.saveAchievement(achievement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(
            @PathVariable Long id,
            @Valid @RequestBody Achievement achievementDetails) {
        Achievement updatedAchievement = achievementService.updateAchievement(id, achievementDetails);
        return ResponseEntity.ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
