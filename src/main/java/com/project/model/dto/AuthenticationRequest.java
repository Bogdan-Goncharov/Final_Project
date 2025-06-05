package com.project.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication request containing username and password.")
public class AuthenticationRequest {
    @NotBlank(message = "Username cannot be empty")
    @Schema(description = "Username for authentication", example = "john_doe")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Schema(description = "Password for authentication", example = "password123")
    private String password;
}
