package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.UserProfileRequestDTO;
import br.edu.bsi.trocaverso.dto.UserProfileResponseDTO;
import br.edu.bsi.trocaverso.model.User;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserProfileServiceTest {

    private UserProfileRepository userProfileRepository;
    private UserProfileService userProfileService;

    private UUID profileId;
    private UserProfile mockProfile;

    @BeforeEach
    void setup() {
        userProfileRepository = mock(UserProfileRepository.class);
        userProfileService = new UserProfileService(userProfileRepository);

        // Perfil fictício
        profileId = UUID.randomUUID();
        mockProfile = new UserProfile();
        mockProfile.setProfileBio("Bio original");
        mockProfile.setProfilePicturePath("img.jpg");

        User user = new User();
        user.setId(UUID.randomUUID());
        mockProfile.setUserAccount(user);

        mockProfile.setId(profileId);
    }

    @Test
    void deveBuscarPerfilPorId() {
        when(userProfileRepository.findById(profileId)).thenReturn(Optional.of(mockProfile));

        UserProfileResponseDTO dto = userProfileService.findById(profileId);

        assertEquals("Bio original", dto.getProfileBio());
        assertEquals("img.jpg", dto.getProfilePicturePath());
    }

    @Test
    void deveLancarExcecaoSePerfilNaoExiste() {
        when(userProfileRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userProfileService.findById(UUID.randomUUID()));
    }

    @Test
    void deveListarTodosOsPerfis() {
        when(userProfileRepository.findAll()).thenReturn(List.of(mockProfile));

        List<UserProfileResponseDTO> result = userProfileService.findAll();

        assertEquals(1, result.size());
        assertEquals("Bio original", result.get(0).getProfileBio());
    }

    @Test
    void deveAtualizarPerfil() {
        when(userProfileRepository.findById(profileId)).thenReturn(Optional.of(mockProfile));
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(mockProfile);

        UserProfileRequestDTO dto = new UserProfileRequestDTO();
        dto.setProfileBio("Nova Bio");
        dto.setProfilePicturePath("nova.png");

        UserProfileResponseDTO updated = userProfileService.update(profileId, dto);

        assertEquals("Nova Bio", updated.getProfileBio());
        assertEquals("nova.png", updated.getProfilePicturePath());
    }
}
