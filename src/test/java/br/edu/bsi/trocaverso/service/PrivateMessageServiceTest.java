package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.PrivateMessageRequestDTO;
import br.edu.bsi.trocaverso.dto.PrivateMessageResponseDTO;
import br.edu.bsi.trocaverso.model.PrivateMessage;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.PrivateMessageRepository;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrivateMessageServiceTest {

    private PrivateMessageRepository messageRepository;
    private UserProfileRepository userProfileRepository;
    private PrivateMessageService service;

    private UUID senderId = UUID.randomUUID();
    private UUID receiverId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        messageRepository = mock(PrivateMessageRepository.class);
        userProfileRepository = mock(UserProfileRepository.class);
        service = new PrivateMessageService(messageRepository, userProfileRepository);
    }

    @Test
    void create_ReturnsDTO_WhenSenderAndReceiverExist() {
        // Arrange
        UserProfile sender = new UserProfile(); sender.setId(senderId);
        UserProfile receiver = new UserProfile(); receiver.setId(receiverId);

        when(userProfileRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(userProfileRepository.findById(receiverId)).thenReturn(Optional.of(receiver));

        PrivateMessageRequestDTO dto = new PrivateMessageRequestDTO();
        dto.setSenderId(senderId);
        dto.setReceiverId(receiverId);
        dto.setContent("Olá!");
        dto.setDate(LocalDate.now().toString());

        PrivateMessage saved = new PrivateMessage();
        saved.setId(UUID.randomUUID());
        saved.setSender(sender);
        saved.setReceiver(receiver);
        saved.setContent(dto.getContent());
        saved.setDate(LocalDate.parse(dto.getDate()));

        when(messageRepository.save(any())).thenReturn(saved);

        // Act
        PrivateMessageResponseDTO result = service.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(senderId, result.getSenderId());
        assertEquals(receiverId, result.getReceiverId());
        assertEquals(dto.getContent(), result.getContent());
        assertEquals(LocalDate.parse(dto.getDate()), result.getDate());
    }

    @Test
    void create_ReturnsNull_WhenUserNotFound() {
        // Arrange
        PrivateMessageRequestDTO dto = new PrivateMessageRequestDTO();
        dto.setSenderId(senderId);
        dto.setReceiverId(receiverId);
        dto.setContent("Teste");
        dto.setDate(LocalDate.now().toString());

        when(userProfileRepository.findById(senderId)).thenReturn(Optional.empty());

        // Act
        PrivateMessageResponseDTO result = service.create(dto);

        // Assert
        assertNull(result);
    }

    @Test
    void send_ThrowsException_WhenSenderNotFound() {
        // Arrange
        PrivateMessageRequestDTO dto = new PrivateMessageRequestDTO();
        dto.setSenderId(senderId);
        dto.setReceiverId(receiverId);
        dto.setContent("Mensagem automática");

        when(userProfileRepository.findById(senderId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.send(dto));
        assertEquals("Remetente não encontrado", ex.getMessage());
    }

    @Test
    void send_ThrowsException_WhenReceiverNotFound() {
        // Arrange
        UserProfile sender = new UserProfile(); sender.setId(senderId);
        PrivateMessageRequestDTO dto = new PrivateMessageRequestDTO();
        dto.setSenderId(senderId);
        dto.setReceiverId(receiverId);
        dto.setContent("Mensagem automática");

        when(userProfileRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(userProfileRepository.findById(receiverId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.send(dto));
        assertEquals("Destinatário não encontrado", ex.getMessage());
    }
}
