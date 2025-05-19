package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.PrivateMessageRequestDTO;
import br.edu.bsi.trocaverso.dto.PrivateMessageResponseDTO;
import br.edu.bsi.trocaverso.service.PrivateMessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/private-messages")
public class PrivateMessageController {

    private final PrivateMessageService privateMessageService;

    public PrivateMessageController(PrivateMessageService privateMessageService) {
        this.privateMessageService = privateMessageService;
    }

    @PostMapping
    public ResponseEntity<PrivateMessageResponseDTO> sendMessage(@Valid @RequestBody PrivateMessageRequestDTO dto) {
        PrivateMessageResponseDTO created = privateMessageService.send(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivateMessageResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(privateMessageService.findById(id));
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<PrivateMessageResponseDTO>> getConversation(
            @RequestParam UUID senderId,
            @RequestParam UUID receiverId) {
        return ResponseEntity.ok(privateMessageService.findConversation(senderId, receiverId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        privateMessageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
