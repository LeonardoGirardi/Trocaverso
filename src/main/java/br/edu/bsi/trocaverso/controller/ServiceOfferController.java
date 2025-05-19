package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.ServiceOfferRequestDTO;
import br.edu.bsi.trocaverso.dto.ServiceOfferResponseDTO;
import br.edu.bsi.trocaverso.service.ServiceOfferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-offers")
public class ServiceOfferController {

    private final ServiceOfferService serviceOfferService;

    public ServiceOfferController(ServiceOfferService serviceOfferService) {
        this.serviceOfferService = serviceOfferService;
    }

    @PostMapping
    public ResponseEntity<ServiceOfferResponseDTO> create(@Valid @RequestBody ServiceOfferRequestDTO dto) {
        ServiceOfferResponseDTO created = serviceOfferService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOfferResponseDTO> getById(@PathVariable UUID id) {
        ServiceOfferResponseDTO response = serviceOfferService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ServiceOfferResponseDTO>> getAll() {
        return ResponseEntity.ok(serviceOfferService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOfferResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody ServiceOfferRequestDTO dto) {
        ServiceOfferResponseDTO updated = serviceOfferService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        serviceOfferService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
