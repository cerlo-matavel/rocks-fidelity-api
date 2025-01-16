package org.rocks.rocksfidelity.user;

import org.rocks.rocksfidelity.user.client.Client;
import org.rocks.rocksfidelity.user.client.DTO.ClientRequestDTO;
import org.rocks.rocksfidelity.user.client.DTO.ClientResponseDTO;
import org.rocks.rocksfidelity.user.manager.DTO.ManagerCreateDTO;
import org.rocks.rocksfidelity.user.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository clientRepository;
    private UserService userService;

    @Autowired
    public UserController(//ClientRepository clientRepository,
                          UserService userService
                          ) {
//        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> registerClient(@RequestBody ClientRequestDTO client ){
    try {
        Optional<Client> newClient = userService.createClient(client);

        System.out.println(newClient.get());

        if(newClient.isPresent()){
            return ResponseEntity.status(201).body(new ClientResponseDTO(newClient.get().getId()));
        }
        return ResponseEntity.status(400).body("Couldn't create client");
    }catch (Exception e){
        return ResponseEntity.status(400).body("User already exists");
    }

    }

    @PostMapping("/managers")
    public ResponseEntity<?> registerManager(@RequestBody ManagerCreateDTO manager ){

        Optional<Manager> newClient = userService.createManager(manager);

        System.out.println(newClient.get());

        if(newClient.isPresent()){
            return ResponseEntity.status(201).body(new ClientResponseDTO(newClient.get().getId()));
        }
        return ResponseEntity.status(400).body("Couldn't create Manager");
    }
}
