package org.rocks.rocksfidelity.transaction.type;

import jakarta.persistence.Entity;

@Entity
public class Transference extends Transaction{

    private String source;
    private String target;
    private int amount;

    public Transference() {}

    public Transference(String source, String target, int amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getAmount() {
        return amount;
    }

}
