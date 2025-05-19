package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.ServiceTradeProposalRequestDTO;
import br.edu.bsi.trocaverso.dto.ServiceTradeProposalResponseDTO;
import br.edu.bsi.trocaverso.model.TradeProposalStatus;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTradeProposalServiceTest {

    private ServiceTradeProposalRepository proposalRepository;
    private UserProfileRepository userProfileRepository;
    private ServiceOfferRepository serviceOfferRepository;
    private PrivateMessageRepository privateMessageRepository;

    private ServiceTradeProposalService service;

    private final UUID proposerId = UUID.randomUUID();
    private final UUID receiverId = UUID.randomUUID();
    private final UUID offeredId = UUID.randomUUID();
    private final UUID requestedId = UUID.randomUUID();
    private final UUID messageId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        proposalRepository = mock(ServiceTradeProposalRepository.class);
        userProfileRepository = mock(UserProfileRepository.class);
        serviceOfferRepository = mock(ServiceOfferRepository.class);
        privateMessageRepository = mock(PrivateMessageRepository.class);

        service = new ServiceTradeProposalService(
                proposalRepository,
                userProfileRepository,
                serviceOfferRepository,
                privateMessageRepository
        );
    }

    @Test
    void create_ReturnsDTO_WhenValidInput() {
        // Arrange
        UserProfile proposer = new UserProfile(); proposer.setId(proposerId);
        UserProfile receiver = new UserProfile(); receiver.setId(receiverId);
        ServiceOffer offered = new ServiceOffer(); offered.setId(offeredId);
        ServiceOffer requested = new ServiceOffer(); requested.setId(requestedId);
        PrivateMessage message = new PrivateMessage(); message.setId(messageId);

        when(userProfileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));
        when(userProfileRepository.findById(receiverId)).thenReturn(Optional.of(receiver));
        when(serviceOfferRepository.findById(offeredId)).thenReturn(Optional.of(offered));
        when(serviceOfferRepository.findById(requestedId)).thenReturn(Optional.of(requested));
        when(privateMessageRepository.findById(messageId)).thenReturn(Optional.of(message));

        ServiceTradeProposal saved = new ServiceTradeProposal();
        saved.setId(UUID.randomUUID());
        saved.setProposer(proposer);
        saved.setReceiver(receiver);
        saved.setServiceOffered(offered);
        saved.setServiceRequested(requested);
        saved.setPrivateMessage(message);
        saved.setStatus(TradeProposalStatus.PENDING);

        when(proposalRepository.save(any())).thenReturn(saved);

        ServiceTradeProposalRequestDTO dto = new ServiceTradeProposalRequestDTO();
        dto.setProposerId(proposerId);
        dto.setReceiverId(receiverId);
        dto.setServiceOfferedId(offeredId);
        dto.setServiceRequestedId(requestedId);
        dto.setPrivateMessageId(messageId);
        dto.setStatus("PENDING");

        // Act
        ServiceTradeProposalResponseDTO result = service.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(proposerId, result.getProposerId());
        assertEquals(receiverId, result.getReceiverId());
        assertEquals(offeredId, result.getServiceOfferedId());
        assertEquals(requestedId, result.getServiceRequestedId());
        assertEquals(messageId, result.getPrivateMessageId());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    void create_ReturnsNull_WhenMissingEntities() {
        // Arrange
        when(userProfileRepository.findById(proposerId)).thenReturn(Optional.empty());

        ServiceTradeProposalRequestDTO dto = new ServiceTradeProposalRequestDTO();
        dto.setProposerId(proposerId);
        dto.setReceiverId(receiverId);
        dto.setServiceOfferedId(offeredId);
        dto.setServiceRequestedId(requestedId);
        dto.setPrivateMessageId(messageId);

        // Act
        ServiceTradeProposalResponseDTO result = service.create(dto);

        // Assert
        assertNull(result);
    }

    @Test
    void updateStatus_ChangesStatusCorrectly() {
        // Arrange
        UUID proposalId = UUID.randomUUID();

        UserProfile proposer = new UserProfile(); proposer.setId(proposerId);
        UserProfile receiver = new UserProfile(); receiver.setId(receiverId);
        ServiceOffer offered = new ServiceOffer(); offered.setId(offeredId);
        ServiceOffer requested = new ServiceOffer(); requested.setId(requestedId);
        PrivateMessage message = new PrivateMessage(); message.setId(messageId);

        ServiceTradeProposal proposal = new ServiceTradeProposal();
        proposal.setId(proposalId);
        proposal.setProposer(proposer);
        proposal.setReceiver(receiver);
        proposal.setServiceOffered(offered);
        proposal.setServiceRequested(requested);
        proposal.setPrivateMessage(message);
        proposal.setStatus(TradeProposalStatus.PENDING);

        when(proposalRepository.findById(proposalId)).thenReturn(Optional.of(proposal));
        when(proposalRepository.save(any())).thenReturn(proposal);

        // Act
        ServiceTradeProposalResponseDTO updated = service.updateStatus(proposalId, "ACCEPTED");

        // Assert
        assertNotNull(updated);
        assertEquals("ACCEPTED", updated.getStatus());
    }

    @Test
    void updateStatus_ThrowsException_OnInvalidStatus() {
        UUID id = UUID.randomUUID();
        ServiceTradeProposal proposal = new ServiceTradeProposal();
        proposal.setId(id);
        proposal.setStatus(TradeProposalStatus.PENDING);

        when(proposalRepository.findById(id)).thenReturn(Optional.of(proposal));

        assertThrows(RuntimeException.class, () -> {
            service.updateStatus(id, "INVALID");
        });
    }
}
