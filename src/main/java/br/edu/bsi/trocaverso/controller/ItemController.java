package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.ItemRequestDTO;
import br.edu.bsi.trocaverso.dto.ItemResponseDTO;
import br.edu.bsi.trocaverso.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> create(@Valid @RequestBody ItemRequestDTO dto) {
        ItemResponseDTO createdItem = itemService.create(dto);
        return ResponseEntity.status(201).body(createdItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getById(@PathVariable UUID id) {
        ItemResponseDTO item = itemService.findById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody ItemRequestDTO dto) {
        ItemResponseDTO updated = itemService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}