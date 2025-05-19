package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.model.Location;
import br.edu.bsi.trocaverso.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    private LocationRepository locationRepository;
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationRepository = mock(LocationRepository.class);
        locationService = new LocationService(locationRepository);
    }

    @Test
    void findById_ReturnsLocation_WhenExists() {
        // Arrange
        UUID locationId = UUID.randomUUID();
        Location location = new Location();
        location.setId(locationId);

        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        // Act
        Location result = locationService.findById(locationId);

        // Assert
        assertNotNull(result);
        assertEquals(locationId, result.getId());
    }

    @Test
    void findById_ThrowsException_WhenNotFound() {
        // Arrange
        UUID locationId = UUID.randomUUID();
        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            locationService.findById(locationId);
        });

        assertEquals("Localização não encontrada: " + locationId, exception.getMessage());
    }
}
