package org.rocks.rocksfidelity.transaction;

import java.time.LocalDateTime;

public record BalanceDTO(int totalAvailablePoints, int totalAccumulatedPoints,
                         LocalDateTime expirationDate,LocalDateTime lastTransactionDate) {
}
