package org.rocks.rocksfidelity.transaction;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransferenceDTO(
        @Email(message = "Invalid source email format")
        @NotNull(message = "Source email is required")
        String source,

        @Email(message = "Invalid target email format")
        @NotNull(message = "Target email is required")
        String target,

        @Min(value = 1, message = "Transfer amount must be at least 1")
        int amount) {
}
