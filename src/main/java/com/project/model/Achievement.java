package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "achievements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Achievement entity representing a user achievement in the system.")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the achievement", example = "1")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Schema(description = "Title of the achievement", example = "First Blood")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Schema(description = "Detailed description of the achievement", example = "Awarded for the first kill in the game")
    private String description;

    @Column(nullable = false)
    @Min(value = 0, message = "Points cannot be negative")
    @Schema(description = "Points awarded for this achievement", example = "100")
    private int points;
}

