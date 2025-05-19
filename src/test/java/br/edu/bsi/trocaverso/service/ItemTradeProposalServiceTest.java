package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.ItemTradeProposalRequestDTO;
import br.edu.bsi.trocaverso.dto.ItemTradeProposalResponseDTO;
import br.edu.bsi.trocaverso.model.TradeProposalStatus;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemTradeProposalServiceTest {

    private ItemTradeProposalRepository proposalRepository;
    private UserProfileRepository userProfileRepository;
    private ItemRepository itemRepository;
    private PrivateMessageRepository messageRepository;
    private ItemTradeProposalService service;

    private final UUID proposerId = UUID.randomUUID();
    private final UUID receiverId = UUID.randomUUID();
    private final UUID itemOfferedId = UUID.randomUUID();
    private final UUID itemRequestedId = UUID.randomUUID();
    private final UUID messageId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        proposalRepository = mock(ItemTradeProposalRepository.class);
        userProfileRepository = mock(UserProfileRepository.class);
        itemRepository = mock(ItemRepository.class);
        messageRepository = mock(PrivateMessageRepository.class);

        service = new ItemTradeProposalService(proposalRepository, userProfileRepository, itemRepository, messageRepository);
    }

    @Test
    void create_ReturnsDTO_WhenValidInput() {
        // Arrange
        UserProfile proposer = new UserProfile(); proposer.setId(proposerId);
        UserProfile receiver = new UserProfile(); receiver.setId(receiverId);
        Item offered = new Item(); offered.setId(itemOfferedId);
        Item requested = new Item(); requested.setId(itemRequestedId);
        PrivateMessage message = new PrivateMessage(); message.setId(messageId);

        when(userProfileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));
        when(userProfileRepository.findById(receiverId)).thenReturn(Optional.of(receiver));
        when(itemRepository.findById(itemOfferedId)).thenReturn(Optional.of(offered));
        when(itemRepository.findById(itemRequestedId)).thenReturn(Optional.of(requested));
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));

        ItemTradeProposal saved = new ItemTradeProposal();
        saved.setId(UUID.randomUUID());
        saved.setProposer(proposer);
        saved.setReceiver(receiver);
        saved.setItemOffered(offered);
        saved.setItemRequested(requested);
        saved.setPrivateMessage(message);
        saved.setStatus(TradeProposalStatus.PENDING);

        when(proposalRepository.save(any())).thenReturn(saved);

        ItemTradeProposalRequestDTO dto = new ItemTradeProposalRequestDTO();
        dto.setProposerId(proposerId);
        dto.setReceiverId(receiverId);
        dto.setItemOfferedId(itemOfferedId);
        dto.setItemRequestedId(itemRequestedId);
        dto.setPrivateMessageId(messageId);

        // Act
        ItemTradeProposalResponseDTO result = service.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(proposerId, result.getProposerId());
        assertEquals(receiverId, result.getReceiverId());
        assertEquals(itemOfferedId, result.getItemOfferedId());
        assertEquals(itemRequestedId, result.getItemRequestedId());
        assertEquals(messageId, result.getPrivateMessageId());
    }

    @Test
    void create_ReturnsNull_WhenAnyEntityNotFound() {
        // Arrange
        when(userProfileRepository.findById(proposerId)).thenReturn(Optional.empty());

        ItemTradeProposalRequestDTO dto = new ItemTradeProposalRequestDTO();
        dto.setProposerId(proposerId);
        dto.setReceiverId(receiverId);
        dto.setItemOfferedId(itemOfferedId);
        dto.setItemRequestedId(itemRequestedId);
        dto.setPrivateMessageId(messageId);

        // Act
        ItemTradeProposalResponseDTO result = service.create(dto);

        // Assert
        assertNull(result);
    }

    @Test
    void updateStatus_ChangesStatus_WhenValid() {
        // Arrange
        UUID id = UUID.randomUUID();
        ItemTradeProposal proposal = new ItemTradeProposal();
        proposal.setId(id);
        proposal.setStatus(TradeProposalStatus.PENDING);
        proposal.setProposer(new UserProfile()); // evitar NPE no DTO
        proposal.setReceiver(new UserProfile());
        proposal.setItemOffered(new Item());
        proposal.setItemRequested(new Item());

        when(proposalRepository.findById(id)).thenReturn(Optional.of(proposal));
        when(proposalRepository.save(any())).thenReturn(proposal);

        // Act
        ItemTradeProposalResponseDTO result = service.updateStatus(id, "ACCEPTED");

        // Assert
        assertNotNull(result);
        assertEquals("ACCEPTED", result.getStatus());
    }

    @Test
    void updateStatus_ThrowsException_WhenInvalidStatus() {
        // Arrange
        UUID id = UUID.randomUUID();
        ItemTradeProposal proposal = new ItemTradeProposal();
        proposal.setId(id);
        proposal.setStatus(TradeProposalStatus.PENDING);
        proposal.setProposer(new UserProfile());
        proposal.setReceiver(new UserProfile());
        proposal.setItemOffered(new Item());
        proposal.setItemRequested(new Item());

        when(proposalRepository.findById(id)).thenReturn(Optional.of(proposal));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.updateStatus(id, "INVALID_STATUS")
        );
        assertEquals("Status inválido: INVALID_STATUS", exception.getMessage());
    }
}
