package org.rocks.rocksfidelity.user.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.rocks.rocksfidelity.transaction.BalanceDTO;
import org.rocks.rocksfidelity.transaction.TransactionService;
import org.rocks.rocksfidelity.transaction.TransferenceDTO;
import org.rocks.rocksfidelity.transaction.type.DTO.PurchaseDTO;
import org.rocks.rocksfidelity.user.client.DTO.ClientEmailDTO;
import org.rocks.rocksfidelity.user.client.DTO.ClientPurchaseDTO;
import org.rocks.rocksfidelity.user.client.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class ClientController {

    private ClientService clientService;
    private TransactionService transactionService;

    @Autowired
    public ClientController(ClientService clientService, TransactionService transactionService) {
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @Operation(summary = "Register a new purchase for a client", description = "Registers a purchase by email, deducting points and cash.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Purchase registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientPurchaseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Transaction failed", content = @Content)
    })
    @PostMapping("/clients/purchase")
    public ResponseEntity<?> registerPurchase(@Valid @RequestBody ClientPurchaseDTO purchaseDTO) {

        Optional<ClientPurchaseDTO> purchase = clientService.makeAPurchase(purchaseDTO.email(),
                new PurchaseDTO(purchaseDTO.amountCash(),
                        purchaseDTO.amountPoints()
                )
        );

        if (purchase.isPresent()) {
            return ResponseEntity.status(200).body(purchase.get());
        }
        return ResponseEntity.status(400).body("Transaction Failed");
    }

    @Operation(summary = "View a client's balance", description = "Retrieves the balance of a client by email.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @GetMapping("/clients")
    public ResponseEntity<?> seeBalance(@Valid @RequestBody ClientEmailDTO clientEmailDTO) {

        Optional<BalanceDTO> balance = this.transactionService.getBalance(clientEmailDTO.email());

        if (balance.isPresent()) {
            return ResponseEntity.status(200).body(balance.get());
        }
        return ResponseEntity.status(404).body("Client not found");
    }

    @Operation(summary = "Transfer points between clients", description = "Transfers points from one client to another.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Points transferred successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransferenceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @PostMapping("/clients/transfer")
    public ResponseEntity<?> transferPoints(@Valid @RequestBody TransferenceDTO transferenceDTO) throws ClientNotFoundException {

        Optional<TransferenceDTO> transference = this.transactionService.transferPoints(transferenceDTO.source(),
                transferenceDTO.target(),
                transferenceDTO.amount());

        if (transference.isPresent()) {
            return ResponseEntity.status(200).body(transference.get());
        }

        return ResponseEntity.status(404).body("Transfer Failed");
    }
}
