package org.rocks.rocksfidelity.security;

import jakarta.persistence.*;
import org.rocks.rocksfidelity.user.User;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
