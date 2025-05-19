package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.UserReviewRequestDTO;
import br.edu.bsi.trocaverso.dto.UserReviewResponseDTO;
import br.edu.bsi.trocaverso.service.UserReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-reviews")
public class UserReviewController {

    private final UserReviewService userReviewService;

    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @PostMapping
    public ResponseEntity<UserReviewResponseDTO> create(@Valid @RequestBody UserReviewRequestDTO dto) {
        UserReviewResponseDTO created = userReviewService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReviewResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userReviewService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserReviewResponseDTO>> getAll() {
        return ResponseEntity.ok(userReviewService.findAll());
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<UserReviewResponseDTO>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userReviewService.findByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userReviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

