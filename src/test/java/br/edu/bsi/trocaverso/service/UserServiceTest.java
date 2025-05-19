
package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.UserRequestDTO;
import br.edu.bsi.trocaverso.dto.UserResponseDTO;
import br.edu.bsi.trocaverso.model.Location;
import br.edu.bsi.trocaverso.model.User;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import br.edu.bsi.trocaverso.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private LocationService locationService;
    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userProfileRepository = mock(UserProfileRepository.class);
        locationService = mock(LocationService.class);
        userService = new UserService(userRepository, locationService);
        userService.userProfileRepository = userProfileRepository;
    }

    @Test
    void deveCriarUsuarioComPerfil() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setUserName("Leo");
        dto.setUserEmail("leo@email.com");
        dto.setUserPassword("123");
        UUID locationId = UUID.randomUUID();
        dto.setLocationId(locationId);

        Location location = new Location();
        location.setId(locationId);
        when(locationService.findById(locationId)).thenReturn(location);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName("Leo");
        user.setUserEmail("leo@email.com");
        user.setUserPassword("123");
        user.setLocation(location);

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.create(dto);

        assertNotNull(response.getId());
        assertEquals("Leo", response.getUserName());
        assertEquals(locationId, response.getLocationId());
        verify(userProfileRepository).save(any(UserProfile.class));
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(id));
    }

    @Test
    void deveLancarExcecaoSeUsuarioNaoExiste() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findById(id));
    }

    @Test
    void deveAtualizarUsuario() {
        UUID id = UUID.randomUUID();
        Location location = new Location();
        location.setId(UUID.randomUUID());

        User user = new User();
        user.setId(id);
        user.setLocation(location);
        user.setUserName("Antigo");
        user.setUserEmail("antigo@email.com");
        user.setUserPassword("123");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(locationService.findById(any())).thenReturn(location);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserRequestDTO dto = new UserRequestDTO();
        dto.setUserName("Novo");
        dto.setUserEmail("novo@email.com");
        dto.setUserPassword("321");
        dto.setLocationId(location.getId());

        UserResponseDTO updated = userService.update(id, dto);

        assertEquals("Novo", updated.getUserName());
        assertEquals("novo@email.com", updated.getUserEmail());
        assertEquals(location.getId(), updated.getLocationId());
    }
}
