package com.project.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;


@Setter
@Getter
@Data
@Schema(description = "Authentication response containing JWT token.")
public class AuthenticationResponse {
    @Schema(description = "JWT token for authenticated user", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
