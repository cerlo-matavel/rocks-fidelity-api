package org.rocks.rocksfidelity.transaction.type;

import jakarta.persistence.*;
import org.rocks.rocksfidelity.user.client.Client;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
