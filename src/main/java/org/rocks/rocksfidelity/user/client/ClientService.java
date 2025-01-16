package org.rocks.rocksfidelity.user.client;

import org.rocks.rocksfidelity.transaction.Balance;
import org.rocks.rocksfidelity.transaction.type.DTO.PurchaseDTO;
import org.rocks.rocksfidelity.transaction.type.Purchase;
import org.rocks.rocksfidelity.user.client.DTO.ClientPurchaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository userRepository;

    public ClientService(ClientRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<ClientPurchaseDTO> makeAPurchase(String clientEmail, PurchaseDTO newPurchase) {
        Optional<Client> clientOpt = userRepository.findByEmail(clientEmail);

        if (clientOpt.isEmpty()) {
            return Optional.empty();
        }

        Client client = clientOpt.get();
        Balance balance = client.getBalance();

        int newAvailablePoints = balance.getTotalAvailablePoints() + 5;
        balance.setTotalAvailablePoints(newAvailablePoints);

        LocalDateTime time  = LocalDateTime.now().plusDays(60);
        balance.setExpirationDate(time);

        int newAccumulatedPoints = balance.getTotalAccumulatedPoints() + 5;
        balance.setTotalAccumulatedPoints(newAccumulatedPoints);

        Purchase purchase = new Purchase(newPurchase.amountCash(), newPurchase.amountPoints(), client);
        client.addTransaction(purchase);

        userRepository.save(client);

        ClientPurchaseDTO responseDTO = new ClientPurchaseDTO(client.getEmail(),
                newPurchase.amountCash(),
                newPurchase.amountPoints());

        return Optional.of(responseDTO);
    }
}
