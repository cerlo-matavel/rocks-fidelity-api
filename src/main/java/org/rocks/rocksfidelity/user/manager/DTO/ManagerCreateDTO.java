package org.rocks.rocksfidelity.user.manager.DTO;

public record ManagerCreateDTO(String username, String password, String email, String phoneNumber,
                               String firstName, String lastName) {
}
