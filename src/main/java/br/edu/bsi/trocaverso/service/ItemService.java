package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserProfileRepository userProfileRepository;

    public ItemService(ItemRepository itemRepository, UserProfileRepository userProfileRepository) {
        this.itemRepository = itemRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public ItemResponseDTO create(ItemRequestDTO dto) {
        UserProfile owner = userProfileRepository.findById(dto.getOwnerId()).orElse(null);
        if (owner == null) return null;

        Item item = new Item();
        item.setItemName(dto.getItemName());
        item.setItemDescription(dto.getItemDescription());
        item.setItemPicturePath(dto.getItemPicturePath());
        item.setOwner(owner);
        item = itemRepository.save(item);

        return toDTO(item);
    }

    public List<ItemResponseDTO> findAll() {
        return itemRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ItemResponseDTO findById(UUID id) {
        return itemRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ItemResponseDTO update(UUID id, ItemRequestDTO dto) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) return null;

        UserProfile owner = userProfileRepository.findById(dto.getOwnerId()).orElse(null);
        if (owner == null) return null;

        item.setItemName(dto.getItemName());
        item.setItemDescription(dto.getItemDescription());
        item.setItemPicturePath(dto.getItemPicturePath());
        item.setOwner(owner);
        item = itemRepository.save(item);

        return toDTO(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    private ItemResponseDTO toDTO(Item item) {
        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setItemDescription(item.getItemDescription());
        dto.setItemPicturePath(item.getItemPicturePath());
        dto.setOwnerId(item.getOwner().getId());
        return dto;
    }
}
