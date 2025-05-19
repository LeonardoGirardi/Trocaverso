package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PrivateMessageService {

    private final PrivateMessageRepository messageRepository;
    private final UserProfileRepository userProfileRepository;

    public PrivateMessageService(PrivateMessageRepository messageRepository, UserProfileRepository userProfileRepository) {
        this.messageRepository = messageRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public PrivateMessageResponseDTO create(PrivateMessageRequestDTO dto) {
        UserProfile sender = userProfileRepository.findById(dto.getSenderId()).orElse(null);
        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId()).orElse(null);

        if (sender == null || receiver == null) return null;

        PrivateMessage message = new PrivateMessage();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());
        message.setDate(java.time.LocalDate.parse(dto.getDate()));
        message = messageRepository.save(message);

        return toDTO(message);
    }

    public List<PrivateMessageResponseDTO> findAll() {
        return messageRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PrivateMessageResponseDTO findById(UUID id) {
        return messageRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public PrivateMessageResponseDTO send(PrivateMessageRequestDTO dto) {
        UserProfile sender = userProfileRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));

        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        PrivateMessage message = new PrivateMessage();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());
        message.setDate(LocalDate.now());

        messageRepository.save(message);

        return new PrivateMessageResponseDTO(message);
    }

    public List<PrivateMessageResponseDTO> findConversation(UUID senderId, UUID receiverId) {
        List<PrivateMessage> messages = messageRepository
                .findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByDateAsc(senderId, receiverId, senderId, receiverId);

        return messages.stream()
                .map(PrivateMessageResponseDTO::new)
                .collect(Collectors.toList());
    }


    public PrivateMessageResponseDTO update(UUID id, PrivateMessageRequestDTO dto) {
        PrivateMessage message = messageRepository.findById(id).orElse(null);
        if (message == null) return null;

        UserProfile sender = userProfileRepository.findById(dto.getSenderId()).orElse(null);
        UserProfile receiver = userProfileRepository.findById(dto.getReceiverId()).orElse(null);

        if (sender == null || receiver == null) return null;

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(dto.getContent());
        message.setDate(java.time.LocalDate.parse(dto.getDate()));
        message = messageRepository.save(message);

        return toDTO(message);
    }

    public void delete(UUID id) {
        messageRepository.deleteById(id);
    }

    private PrivateMessageResponseDTO toDTO(PrivateMessage message) {
        PrivateMessageResponseDTO dto = new PrivateMessageResponseDTO(message);
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setContent(message.getContent());
        dto.setDate(message.getDate());
        return dto;
    }
}