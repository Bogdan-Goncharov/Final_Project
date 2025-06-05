package com.project.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "player_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Player statistics entity containing game progress and ranking.")
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for player stats", example = "1")
    private Long id;

    @Min(value = 0, message = "Total points cannot be negative")
    @Schema(description = "Total accumulated points", example = "1500")
    private int totalPoints;

    @Min(value = 0, message = "Total achievements cannot be negative")
    @Schema(description = "Total number of achievements unlocked", example = "5")
    private int totalAchievements;

    @Min(value = 0, message = "Ranking cannot be negative")
    @Schema(description = "Player's ranking position", example = "10")
    private int ranking;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @Schema(description = "User associated with these stats")
    private User user;
}

