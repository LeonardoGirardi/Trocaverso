package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.ItemTradeProposalRequestDTO;
import br.edu.bsi.trocaverso.dto.ItemTradeProposalResponseDTO;
import br.edu.bsi.trocaverso.service.ItemTradeProposalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/item-trade-proposals")
public class ItemTradeProposalController {

    private final ItemTradeProposalService proposalService;

    public ItemTradeProposalController(ItemTradeProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    public ResponseEntity<ItemTradeProposalResponseDTO> create(@Valid @RequestBody ItemTradeProposalRequestDTO dto) {
        ItemTradeProposalResponseDTO response = proposalService.create(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemTradeProposalResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(proposalService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemTradeProposalResponseDTO>> getAll() {
        return ResponseEntity.ok(proposalService.findAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ItemTradeProposalResponseDTO> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(proposalService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        proposalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
