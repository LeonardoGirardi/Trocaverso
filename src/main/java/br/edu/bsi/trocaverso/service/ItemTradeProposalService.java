package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.repository.UserProfileRepository;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemTradeProposalService {

    private final ItemTradeProposalRepository proposalRepository;
    private final UserProfileRepository userProfileRepository;
    private final ItemRepository itemRepository;
    private final PrivateMessageRepository messageRepository;

    public ItemTradeProposalService(ItemTradeProposalRepository proposalRepository, UserProfileRepository userProfileRepository, ItemRepository itemRepository, PrivateMessageRepository messageRepository) {
        this.proposalRepository = proposalRepository;
        this.userProfileRepository = userProfileRepository;
        this.itemRepository = itemRepository;
        this.messageRepository = messageRepository;
    }

    public ItemTradeProposalResponseDTO create(ItemTradeProposalRequestDTO dto) {
        UserProfile proposer = userProfileRepository.findById(dto.getProposerId()).orElse(null);
        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId()).orElse(null);
        Item itemOffered = itemRepository.findById(dto.getItemOfferedId()).orElse(null);
        Item itemRequested = itemRepository.findById(dto.getItemRequestedId()).orElse(null);
        PrivateMessage message = messageRepository.findById(dto.getPrivateMessageId()).orElse(null);

        if (proposer == null || receiver == null || itemOffered == null || itemRequested == null) return null;

        ItemTradeProposal proposal = new ItemTradeProposal();
        proposal.setProposer(proposer);
        proposal.setReceiver(receiver);
        proposal.setItemOffered(itemOffered);
        proposal.setItemRequested(itemRequested);
        proposal.setPrivateMessage(message);
        proposal.setStatus(proposal.getStatus());
        proposal = proposalRepository.save(proposal);

        return toDTO(proposal);
    }

    public List<ItemTradeProposalResponseDTO> findAll() {
        return proposalRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ItemTradeProposalResponseDTO findById(UUID id) {
        return proposalRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ItemTradeProposalResponseDTO update(UUID id, ItemTradeProposalRequestDTO dto) {
        ItemTradeProposal proposal = proposalRepository.findById(id).orElse(null);
        if (proposal == null) return null;

        UserProfile proposer = userProfileRepository.findById(dto.getProposerId()).orElse(null);
        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId()).orElse(null);
        Item itemOffered = itemRepository.findById(dto.getItemOfferedId()).orElse(null);
        Item itemRequested = itemRepository.findById(dto.getItemRequestedId()).orElse(null);
        PrivateMessage message = messageRepository.findById(dto.getPrivateMessageId()).orElse(null);

        if (proposer == null || receiver == null || itemOffered == null || itemRequested == null) return null;

        proposal.setProposer(proposer);
        proposal.setReceiver(receiver);
        proposal.setItemOffered(itemOffered);
        proposal.setItemRequested(itemRequested);
        proposal.setPrivateMessage(message);
        proposal.setStatus(proposal.getStatus());
        proposal = proposalRepository.save(proposal);

        return toDTO(proposal);
    }

    public void delete(UUID id) {
        proposalRepository.deleteById(id);
    }

    private ItemTradeProposalResponseDTO toDTO(ItemTradeProposal proposal) {
        ItemTradeProposalResponseDTO dto = new ItemTradeProposalResponseDTO(proposal);
        dto.setId(proposal.getId());
        dto.setProposerId(proposal.getProposer().getId());
        dto.setReceiverId(proposal.getReceiver().getId());
        dto.setItemOfferedId(proposal.getItemOffered().getId());
        dto.setItemRequestedId(proposal.getItemRequested().getId());
        dto.setPrivateMessageId(proposal.getPrivateMessage() != null ? proposal.getPrivateMessage().getId() : null);
        dto.setStatus(proposal.getStatus().toString());
        return dto;
    }


    public ItemTradeProposalResponseDTO updateStatus(UUID id, String status) {
        ItemTradeProposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada: " + id));

        TradeProposalStatus newStatus;
        try {
            newStatus = TradeProposalStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido: " + status);
        }

        proposal.setStatus(newStatus);
        proposalRepository.save(proposal);

        return toDTO(proposal);
    }
}

