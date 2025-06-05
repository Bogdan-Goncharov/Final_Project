package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Validation error response containing field errors")
public class ValidationErrorResponse {
    @Schema(description = "Type of the error", example = "Data validation error")
    private String error;

    @Schema(description = "Map of field errors", example = "{\"username\": \"Username cannot be empty\"}")
    private Map<String, String> fieldErrors;

    @Schema(description = "HTTP status code", example = "400")
    private int status;
}
