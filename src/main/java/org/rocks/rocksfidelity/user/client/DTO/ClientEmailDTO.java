package org.rocks.rocksfidelity.user.client.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClientEmailDTO(
        @Email(message = "Invalid email format")
        @NotNull(message = "Email is required")
        String email) {
}
