package org.rocks.rocksfidelity.transaction.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("SELECT t FROM Transaction t WHERE t.client.id = :clientId ORDER BY t.timestamp DESC")
    List<Transaction> findAllByClientId(@Param("clientId") UUID clientId);
}
