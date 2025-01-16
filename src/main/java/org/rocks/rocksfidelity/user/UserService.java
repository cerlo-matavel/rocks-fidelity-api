package org.rocks.rocksfidelity.user;

import org.rocks.rocksfidelity.security.Credential;
import org.rocks.rocksfidelity.security.RoleEnum;
import org.rocks.rocksfidelity.transaction.Balance;
import org.rocks.rocksfidelity.user.client.Client;
import org.rocks.rocksfidelity.user.client.DTO.ClientRequestDTO;
import org.rocks.rocksfidelity.user.manager.DTO.ManagerCreateDTO;
import org.rocks.rocksfidelity.user.manager.Manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<Client> createClient(ClientRequestDTO clientRequestDTO){
        Client client = new Client();
        client.setEmail(clientRequestDTO.email());
        client.setFirstName(clientRequestDTO.firstName());
        client.setLastName(clientRequestDTO.lastName());
        client.setPhoneNumber(clientRequestDTO.phoneNumber());

        Credential credential = new Credential();
        credential.setUsername(clientRequestDTO.username());
        credential.setPassword(clientRequestDTO.password());
        credential.setRole(RoleEnum.CLIENT);

        client.setCredential(credential);

        Balance balance = new Balance();
        client.setBalance(balance);

        return Optional.of(userRepository.save(client));
    }

    @Transactional
    public Optional<Manager> createManager(ManagerCreateDTO managerCreateDTO){
        Manager manager = new Manager();
        manager.setEmail(managerCreateDTO.email());
        manager.setFirstName(managerCreateDTO.firstName());
        manager.setLastName(managerCreateDTO.lastName());
        manager.setPhoneNumber(managerCreateDTO.phoneNumber());

        Credential credential = new Credential();
        credential.setUsername(managerCreateDTO.username());
        credential.setPassword(managerCreateDTO.password());
        credential.setRole(RoleEnum.MANAGER);

        manager.setCredential(credential);

        return Optional.of(userRepository.save(manager));
    }
}
