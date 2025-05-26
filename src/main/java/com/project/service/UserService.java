package com.project.service;

import com.project.model.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.project.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
        return userRepository.save(user);
    }

}
