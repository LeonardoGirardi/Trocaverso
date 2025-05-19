package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.UserProfileRequestDTO;
import br.edu.bsi.trocaverso.dto.UserProfileResponseDTO;
import br.edu.bsi.trocaverso.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userProfileService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponseDTO>> getAll() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody UserProfileRequestDTO dto) {
        return ResponseEntity.ok(userProfileService.update(id, dto));
    }
}