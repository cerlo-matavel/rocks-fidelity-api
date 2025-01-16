package org.rocks.rocksfidelity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
@RequestMapping("/fidelity")
public class RocksFidelityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocksFidelityApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<?> index() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to Rocks Fidelity lalalal");
        return ResponseEntity.ok(response);
    }
}
