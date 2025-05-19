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
public class ServiceTradeProposalService {

    private final ServiceTradeProposalRepository proposalRepository;
    private final UserProfileRepository userProfileRepository;
    private final ServiceOfferRepository serviceOfferRepository;
    private final PrivateMessageRepository messageRepository;

    public ServiceTradeProposalService(ServiceTradeProposalRepository proposalRepository, UserProfileRepository userProfileRepository, ServiceOfferRepository serviceOfferRepository, PrivateMessageRepository messageRepository) {
        this.proposalRepository = proposalRepository;
        this.userProfileRepository = userProfileRepository;
        this.serviceOfferRepository = serviceOfferRepository;
        this.messageRepository = messageRepository;
    }

    public ServiceTradeProposalResponseDTO create(ServiceTradeProposalRequestDTO dto) {
        UserProfile proposer = userProfileRepository.findById(dto.getProposerId()).orElse(null);
        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId()).orElse(null);
        ServiceOffer serviceOffered = serviceOfferRepository.findById(dto.getServiceOfferedId()).orElse(null);
        ServiceOffer serviceRequested = serviceOfferRepository.findById(dto.getServiceRequestedId()).orElse(null);
        PrivateMessage message = messageRepository.findById(dto.getPrivateMessageId()).orElse(null);

        if (proposer == null || receiver == null || serviceOffered == null || serviceRequested == null) return null;

        ServiceTradeProposal proposal = new ServiceTradeProposal();
        proposal.setProposer(proposer);
        proposal.setReceiver(receiver);
        proposal.setServiceOffered(serviceOffered);
        proposal.setServiceRequested(serviceRequested);
        proposal.setPrivateMessage(message);
        try {
            proposal.setStatus(TradeProposalStatus.valueOf(dto.getStatus()));
        } catch (IllegalArgumentException e) {
            proposal.setStatus(TradeProposalStatus.PENDING);
        }
        proposal = proposalRepository.save(proposal);

        return toDTO(proposal);
    }

    public List<ServiceTradeProposalResponseDTO> findAll() {
        return proposalRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ServiceTradeProposalResponseDTO findById(UUID id) {
        return proposalRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ServiceTradeProposalResponseDTO update(UUID id, ServiceTradeProposalRequestDTO dto) {
        ServiceTradeProposal proposal = proposalRepository.findById(id).orElse(null);
        if (proposal == null) return null;

        UserProfile proposer = userProfileRepository.findById(dto.getProposerId()).orElse(null);
        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId()).orElse(null);
        ServiceOffer serviceOffered = serviceOfferRepository.findById(dto.getServiceOfferedId()).orElse(null);
        ServiceOffer serviceRequested = serviceOfferRepository.findById(dto.getServiceRequestedId()).orElse(null);
        PrivateMessage message = messageRepository.findById(dto.getPrivateMessageId()).orElse(null);

        if (proposer == null || receiver == null || serviceOffered == null || serviceRequested == null) return null;

        proposal.setProposer(proposer);
        proposal.setReceiver(receiver);
        proposal.setServiceOffered(serviceOffered);
        proposal.setServiceRequested(serviceRequested);
        proposal.setPrivateMessage(message);
        try {
            proposal.setStatus(TradeProposalStatus.valueOf(dto.getStatus()));
        } catch (IllegalArgumentException e) {
            proposal.setStatus(TradeProposalStatus.PENDING);
        }
        proposal = proposalRepository.save(proposal);

        return toDTO(proposal);
    }

    public void delete(UUID id) {
        proposalRepository.deleteById(id);
    }

    private ServiceTradeProposalResponseDTO toDTO(ServiceTradeProposal proposal) {
        ServiceTradeProposalResponseDTO dto = new ServiceTradeProposalResponseDTO(proposal);
        dto.setId(proposal.getId());
        dto.setProposerId(proposal.getProposer().getId());
        dto.setReceiverId(proposal.getReceiver().getId());
        dto.setServiceOfferedId(proposal.getServiceOffered().getId());
        dto.setServiceRequestedId(proposal.getServiceRequested().getId());
        dto.setPrivateMessageId(proposal.getPrivateMessage() != null ? proposal.getPrivateMessage().getId() : null);
        dto.setStatus(proposal.getStatus().toString());
        return dto;
    }

    public ServiceTradeProposalResponseDTO updateStatus(UUID id, String status) {
        ServiceTradeProposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta de serviço não encontrada: " + id));

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
