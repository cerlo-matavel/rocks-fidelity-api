package org.rocks.rocksfidelity.transaction;

import org.rocks.rocksfidelity.transaction.type.Transaction;
import org.rocks.rocksfidelity.transaction.type.TransactionRepository;
import org.rocks.rocksfidelity.transaction.type.Transference;
import org.rocks.rocksfidelity.user.client.Client;
import org.rocks.rocksfidelity.user.client.ClientRepository;
import org.rocks.rocksfidelity.user.client.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private BalanceRepository balanceRepository;
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(BalanceRepository balanceRepository, ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.balanceRepository = balanceRepository;
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    public Optional<BalanceDTO> getBalance(String email) {
        Optional<Client> clientOpt = clientRepository.findByEmail(email);

        if (clientOpt.isEmpty()) {
            return Optional.empty();
        }
        Balance balance = clientOpt.get().getBalance();

        BalanceDTO balanceDTO = new BalanceDTO(
                balance.getTotalAvailablePoints(),
                balance.getTotalAccumulatedPoints(),
                balance.getExpirationDate(),
                balance.getLastTransactionDate()
        );

        return Optional.of(balanceDTO);
    }

    @Transactional
    public Optional<TransferenceDTO> transferPoints(String senderEmail, String receiverEmail, int points)
            throws ClientNotFoundException,
            IllegalArgumentException {
        Optional<Client> senderOpt = clientRepository.findByEmail(senderEmail);
        Optional<Client> receiverOpt = clientRepository.findByEmail(receiverEmail);

        if (senderOpt.isEmpty()) {
            throw new ClientNotFoundException("The Sender client doesn't exist.");
        }
        if (receiverOpt.isEmpty()) {
            throw new ClientNotFoundException("The Receiver client doesn't exist.");
        }

        Client sender = senderOpt.get();
        Client receiver = receiverOpt.get();

        Balance senderBalance = sender.getBalance();
        Balance receiverBalance = receiver.getBalance();

        if (senderBalance.getTotalAvailablePoints() < points) {
            throw new IllegalArgumentException("Insufficient points for the sender.");
        }

        senderBalance.setTotalAvailablePoints(senderBalance.getTotalAvailablePoints() - points);

        receiverBalance.setTotalAvailablePoints(receiverBalance.getTotalAvailablePoints() + points);
        receiverBalance.setTotalAccumulatedPoints(receiverBalance.getTotalAccumulatedPoints() + points);
        receiverBalance.setExpirationDate(senderBalance.getExpirationDate());

        balanceRepository.save(senderBalance);
        balanceRepository.save(receiverBalance);

        Transference transference = new Transference(senderEmail, receiverEmail, points);
        transference.setClient(sender);

        transactionRepository.save(transference);

        TransferenceDTO transferenceDTO = new TransferenceDTO(senderEmail, receiverEmail, points);

        return Optional.of(transferenceDTO);
    }

    public List<Transaction> getTransactionsByClient(UUID clientId) {
        return transactionRepository.findAllByClientId(clientId);
    }
}
