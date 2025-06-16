package com.project.service;


import com.project.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import com.project.model.Achievement;
import org.springframework.stereotype.Service;
import com.project.repository.AchievementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService {
    private final AchievementRepository achievementRepository;

    public Achievement saveAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement updateAchievement(Long id, Achievement achievementDetails) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found for id: " + id));
        achievement.setTitle(achievementDetails.getTitle());
        achievement.setDescription(achievementDetails.getDescription());
        achievement.setPoints(achievementDetails.getPoints());
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        if (!achievementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Achievement not found for id: " + id);
        }
    }
}
