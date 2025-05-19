package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.model.User;
import br.edu.bsi.trocaverso.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return userService.create(dto);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.delete(id);
    }
}
