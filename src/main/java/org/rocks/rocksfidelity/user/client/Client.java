package org.rocks.rocksfidelity.user.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rocks.rocksfidelity.reward.Reward;
import org.rocks.rocksfidelity.transaction.Balance;
import org.rocks.rocksfidelity.transaction.type.Transaction;
import org.rocks.rocksfidelity.user.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "client_rewards",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id")
    )
    private Set<Reward> rewards = new HashSet<>();

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        transaction.setClient(this);
        transactions.add(transaction);
    }

    public Balance getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
