package com.project.service;


import com.project.model.PlayerStats;
import com.project.model.User;
import com.project.repository.PlayerStatsRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.project.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PlayerStatsRepository playerStatsRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User with this name already exists");
        }

        User savedUser = userRepository.save(user);

        PlayerStats stats = new PlayerStats();
        stats.setTotalPoints(0);
        stats.setTotalAchievements(0);
        stats.setRanking(0);
        stats.setUser(savedUser);

        playerStatsRepository.save(stats);

        return savedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id: " + id));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

}
