package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard error response")
public class ErrorResponse {
    @Schema(description = "Type of the error", example = "Runtime error")
    private String error;

    @Schema(description = "Detailed error message", example = "Some error occurred")
    private String message;

    @Schema(description = "HTTP status code", example = "400")
    private int status;
}
