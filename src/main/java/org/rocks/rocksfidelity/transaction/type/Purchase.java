package org.rocks.rocksfidelity.transaction.type;

import jakarta.persistence.Entity;
import org.rocks.rocksfidelity.user.client.Client;

@Entity
public class Purchase extends Transaction {

    private float amountCash;
    private int amountPoints;

    public Purchase(float amountCash, int amountPoints, Client client) {
        setClient(client);
        this.amountCash = amountCash;
        this.amountPoints = amountPoints;
    }

    public Purchase() {
    }

    public float getAmountCash() {
        return amountCash;
    }

    public int getAmountPoints() {
        return amountPoints;
    }

    public void setAmountCash(float amountCash) {
        this.amountCash = amountCash;
    }

    public void setAmountPoints(int amountPoints) {
        this.amountPoints = amountPoints;
    }
}
