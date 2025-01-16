package org.rocks.rocksfidelity.user.client.DTO;

public record ClientRequestDTO(String username, String password, String email, String phoneNumber,
                               String firstName, String lastName) {
}
