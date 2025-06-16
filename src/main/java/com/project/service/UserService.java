package com.project.service;

import com.project.exception.ResourceNotFoundException;
import com.project.model.Achievement;
import com.project.model.PlayerStats;
import com.project.model.Role;
import com.project.model.User;
import com.project.repository.AchievementRepository;
import com.project.repository.PlayerStatsRepository;
import com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PlayerStatsRepository playerStatsRepository;
    private final AchievementRepository achievementRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User with this name already exists");
        }


        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            user.setRoles(roles);
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));


        User savedUser = userRepository.save(user);


        PlayerStats stats = new PlayerStats();
        stats.setTotalPoints(0);
        stats.setTotalAchievements(0);
        stats.setRanking(0);
        stats.setUser(savedUser);
        playerStatsRepository.save(stats);


        savedUser.setPlayerStats(stats);
        userRepository.save(savedUser);

        return savedUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }


    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for id: " + id));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());

        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public User addAchievementToUser(Long userId, Long achievementId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Achievement achievement = achievementRepository.findById(achievementId).orElseThrow(() -> new RuntimeException("Achievement not found"));

        if (user.getAchievements().contains(achievement)) {
            throw new RuntimeException("The achievement has already been awarded to the user.");
        }


        user.getAchievements().add(achievement);
        userRepository.save(user);


        PlayerStats stats = playerStatsRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User statistics not found"));

        stats.setTotalAchievements(stats.getTotalAchievements() + 1);
        stats.setTotalPoints(stats.getTotalPoints() + achievement.getPoints());

        playerStatsRepository.save(stats);

        return user;
    }
}
