package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.ServiceOfferRequestDTO;
import br.edu.bsi.trocaverso.dto.ServiceOfferResponseDTO;
import br.edu.bsi.trocaverso.model.ServiceOffer;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.ServiceOfferRepository;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceOfferServiceTest {

    private ServiceOfferRepository serviceOfferRepository;
    private UserProfileRepository userProfileRepository;
    private ServiceOfferService service;

    private final UUID providerId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        serviceOfferRepository = mock(ServiceOfferRepository.class);
        userProfileRepository = mock(UserProfileRepository.class);
        service = new ServiceOfferService(serviceOfferRepository, userProfileRepository);
    }

    @Test
    void create_ReturnsDTO_WhenValidInput() {
        // Arrange
        UserProfile provider = new UserProfile();
        provider.setId(providerId);

        ServiceOfferRequestDTO dto = new ServiceOfferRequestDTO();
        dto.setProviderId(providerId);
        dto.setServiceName("Web Design");
        dto.setDescription("Criação de sites profissionais");
        dto.setServicePicturePath("/images/web.png");

        when(userProfileRepository.findById(providerId)).thenReturn(Optional.of(provider));

        ServiceOffer saved = new ServiceOffer();
        saved.setId(UUID.randomUUID());
        saved.setServiceName(dto.getServiceName());
        saved.setDescription(dto.getDescription());
        saved.setServicePicturePath(dto.getServicePicturePath());
        saved.setProvider(provider);

        when(serviceOfferRepository.save(any())).thenReturn(saved);

        // Act
        ServiceOfferResponseDTO result = service.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getServiceName(), result.getServiceName());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getServicePicturePath(), result.getServicePicturePath());
        assertEquals(providerId, result.getProviderId());
    }

    @Test
    void create_ReturnsNull_WhenProviderNotFound() {
        // Arrange
        ServiceOfferRequestDTO dto = new ServiceOfferRequestDTO();
        dto.setProviderId(providerId);

        when(userProfileRepository.findById(providerId)).thenReturn(Optional.empty());

        // Act
        ServiceOfferResponseDTO result = service.create(dto);

        // Assert
        assertNull(result);
    }
}
