package com.project.controller;

import com.project.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;
import com.project.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping("/{username}")
    @Operation(summary = "Get user by username", description = "Returns user by username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users successfully retrieved"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    @Operation(summary = "Create a new user", description = "Adds a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));

    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.userId")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Updates user information by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
    })
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
    @PostMapping("/{userId}/achievements/{achievementId}")
    @Operation(summary = "Add achievement to user",
            description = "Allows adding an achievement to a user by their ID. Accessible only by admin or the user themselves."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Achievement successfully added"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "User or achievement not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<User> addAchievement(
            @PathVariable Long userId,
            @PathVariable Long achievementId) {
        User updatedUser = userService.addAchievementToUser(userId, achievementId);
        return ResponseEntity.ok(updatedUser);
    }
}

