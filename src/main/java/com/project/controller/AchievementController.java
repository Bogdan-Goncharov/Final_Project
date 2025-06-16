package com.project.controller;

import com.project.model.Achievement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;
import com.project.service.AchievementService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
@Tag(name = "Achievements", description = "Operations related to achievements")
public class AchievementController {
    private final AchievementService achievementService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    @Operation(summary = "Get all achievements", description = "Returns a list of all achievements")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of achievements successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Achievements not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })

    public ResponseEntity<List<Achievement>> getAllAchievements() {
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Add a new achievement", description = "Creates a new achievement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Achievement created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
    })
    public ResponseEntity<Achievement> addAchievement(@Valid @RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.saveAchievement(achievement));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update an achievement", description = "Updates an existing achievement by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Achievement updated successfully"),
            @ApiResponse(responseCode = "404", description = "Achievement not found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
    })
    public ResponseEntity<Achievement> updateAchievement(
            @PathVariable Long id,
            @Valid @RequestBody Achievement achievementDetails) {
        Achievement updatedAchievement = achievementService.updateAchievement(id, achievementDetails);
        return ResponseEntity.ok(updatedAchievement);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an achievement", description = "Deletes an achievement by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Achievement deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Achievement not found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
    })
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
