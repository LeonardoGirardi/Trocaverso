package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.ServiceTradeProposalRequestDTO;
import br.edu.bsi.trocaverso.dto.ServiceTradeProposalResponseDTO;
import br.edu.bsi.trocaverso.service.ServiceTradeProposalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-trade-proposals")
public class ServiceTradeProposalController {

    private final ServiceTradeProposalService proposalService;

    public ServiceTradeProposalController(ServiceTradeProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    public ResponseEntity<ServiceTradeProposalResponseDTO> create(@Valid @RequestBody ServiceTradeProposalRequestDTO dto) {
        ServiceTradeProposalResponseDTO response = proposalService.create(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTradeProposalResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(proposalService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceTradeProposalResponseDTO>> getAll() {
        return ResponseEntity.ok(proposalService.findAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ServiceTradeProposalResponseDTO> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(proposalService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        proposalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
