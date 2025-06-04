package com.project.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 0, message = "Total points cannot be negative")
    private int totalPoints;
    @Min(value = 0, message = "Total achievements cannot be negative")
    private int totalAchievements;
    @Min(value = 0, message = "Ranking cannot be negative")
    private int ranking;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


}
