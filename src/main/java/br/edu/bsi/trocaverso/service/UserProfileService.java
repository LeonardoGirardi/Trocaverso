package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.UserProfileRequestDTO;
import br.edu.bsi.trocaverso.dto.UserProfileResponseDTO;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileResponseDTO findById(UUID id) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado: " + id));
        return new UserProfileResponseDTO(profile);
    }

    public List<UserProfileResponseDTO> findAll() {
        return userProfileRepository.findAll().stream()
                .map(UserProfileResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UserProfileResponseDTO update(UUID id, UserProfileRequestDTO dto) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado: " + id));

        profile.setProfileBio(dto.getProfileBio());
        profile.setProfilePicturePath(dto.getProfilePicturePath());

        userProfileRepository.save(profile);
        return new UserProfileResponseDTO(profile);
    }
}
