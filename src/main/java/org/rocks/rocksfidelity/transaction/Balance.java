package org.rocks.rocksfidelity.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int totalAvailablePoints;
    private int totalAccumulatedPoints;
    private LocalDateTime expirationDate;
    private LocalDateTime lastTransactionDate;

    public void setTotalAvailablePoints(int totalAvailablePoints) {
        this.totalAvailablePoints = totalAvailablePoints;
        this.lastTransactionDate = LocalDateTime.now();
    }

    public void setTotalAccumulatedPoints(int totalAccumulatedPoints) {
        this.totalAccumulatedPoints = totalAccumulatedPoints;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getTotalAvailablePoints() {
        return totalAvailablePoints;
    }

    public int getTotalAccumulatedPoints() {
        return totalAccumulatedPoints;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public LocalDateTime getLastTransactionDate() {
        return lastTransactionDate;
    }
}
