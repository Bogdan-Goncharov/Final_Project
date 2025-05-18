package service;

import lombok.RequiredArgsConstructor;
import model.Achievement;
import org.springframework.stereotype.Service;
import repository.AchievementRepository;

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
}
