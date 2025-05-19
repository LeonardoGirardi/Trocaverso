package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.ItemRequestDTO;
import br.edu.bsi.trocaverso.dto.ItemResponseDTO;
import br.edu.bsi.trocaverso.model.Item;
import br.edu.bsi.trocaverso.model.UserProfile;
import br.edu.bsi.trocaverso.repository.ItemRepository;
import br.edu.bsi.trocaverso.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemRepository itemRepository;
    private UserProfileRepository userProfileRepository;
    private ItemService service;

    private final UUID ownerId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        userProfileRepository = mock(UserProfileRepository.class);
        service = new ItemService(itemRepository, userProfileRepository);
    }

    @Test
    void create_ReturnsDTO_WhenOwnerExists() {
        // Arrange
        UserProfile owner = new UserProfile();
        owner.setId(ownerId);

        ItemRequestDTO dto = new ItemRequestDTO();
        dto.setOwnerId(ownerId);
        dto.setItemName("Notebook");
        dto.setItemDescription("Notebook usado, bom estado");
        dto.setItemPicturePath("/img/notebook.png");

        when(userProfileRepository.findById(ownerId)).thenReturn(Optional.of(owner));

        Item savedItem = new Item();
        savedItem.setId(UUID.randomUUID());
        savedItem.setItemName(dto.getItemName());
        savedItem.setItemDescription(dto.getItemDescription());
        savedItem.setItemPicturePath(dto.getItemPicturePath());
        savedItem.setOwner(owner);

        when(itemRepository.save(any())).thenReturn(savedItem);

        // Act
        ItemResponseDTO result = service.create(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getItemName(), result.getItemName());
        assertEquals(dto.getItemDescription(), result.getItemDescription());
        assertEquals(dto.getItemPicturePath(), result.getItemPicturePath());
        assertEquals(ownerId, result.getOwnerId());
    }

    @Test
    void create_ReturnsNull_WhenOwnerNotFound() {
        // Arrange
        ItemRequestDTO dto = new ItemRequestDTO();
        dto.setOwnerId(ownerId);
        when(userProfileRepository.findById(ownerId)).thenReturn(Optional.empty());
        // Act
        ItemResponseDTO result = service.create(dto);
        // Assert
        assertNull(result);
    }
}
