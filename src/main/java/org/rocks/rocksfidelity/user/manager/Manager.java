package org.rocks.rocksfidelity.user.manager;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.rocks.rocksfidelity.reward.Reward;
import org.rocks.rocksfidelity.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Manager extends User {

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reward> rewards = new ArrayList<>();
}
