package org.rocks.rocksfidelity.user.client.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ClientPurchaseDTO(
        @Email(message = "Invalid email format")
        @NotNull(message = "Email is required")
        String email,

        @Min(value = 0, message = "Amount in cash must be at least 0")
        float amountCash,

        @Min(value = 0, message = "Amount in points must be at least 0")
        int amountPoints
) {
}
