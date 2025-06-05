package com.project.controller;

import com.project.model.Achievement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.project.service.AchievementService;

import java.util.List;
@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
@Tag(name = "Achievements", description = "Operations related to achievements")
public class AchievementController {
    private final AchievementService achievementService;

    @GetMapping("/")
    @Operation(summary = "Get all achievements", description = "Returns a list of all achievements")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of achievements successfully retrieved")
    })
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }

    @PostMapping
    @Operation(summary = "Add a new achievement", description = "Creates a new achievement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Achievement created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<Achievement> addAchievement(@Valid @RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.saveAchievement(achievement));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an achievement", description = "Updates an existing achievement by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Achievement updated successfully"),
            @ApiResponse(responseCode = "404", description = "Achievement not found")
    })
    public ResponseEntity<Achievement> updateAchievement(
            @PathVariable Long id,
            @Valid @RequestBody Achievement achievementDetails) {
        Achievement updatedAchievement = achievementService.updateAchievement(id, achievementDetails);
        return ResponseEntity.ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an achievement", description = "Deletes an achievement by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Achievement deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Achievement not found")
    })
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
