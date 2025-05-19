package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.UserRequestDTO;
import br.edu.bsi.trocaverso.dto.UserResponseDTO;
import br.edu.bsi.trocaverso.model.Location;
import br.edu.bsi.trocaverso.model.User;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import br.edu.bsi.trocaverso.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LocationService locationService;

    @Autowired
    UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository, LocationService locationService) {
        this.userRepository = userRepository;
        this.locationService = locationService;
    }

    public UserResponseDTO create(UserRequestDTO dto) {
        Location location = locationService.findById(dto.getLocationId());

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());
        user.setLocation(location);

        // Cria automaticamente o perfil do usuário
        UserProfile profile = new UserProfile();
        profile.setUserAccount(user);
        profile.setProfileBio("Novo usuário no sistema.");
        profile.setProfilePicturePath(null);
        userProfileRepository.save(profile);

        user = userRepository.save(user);

        return toDTO(user);
    }

    public UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getUserEmail());
        dto.setLocationId(user.getLocation().getId());
        return dto;
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findDTOById(UUID id) {
        return toDTO(findById(id));
    }

    public UserResponseDTO update(UUID id, UserRequestDTO dto) {
        User user = findById(id);
        Location location = locationService.findById(dto.getLocationId());

        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());
        user.setLocation(location);

        return toDTO(userRepository.save(user));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}