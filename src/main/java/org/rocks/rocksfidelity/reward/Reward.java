package org.rocks.rocksfidelity.reward;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.rocks.rocksfidelity.user.client.Client;
import org.rocks.rocksfidelity.user.manager.Manager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private int costInPoints;

    @ManyToMany(mappedBy = "rewards")
    private Set<Client> clients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Manager manager;
}
