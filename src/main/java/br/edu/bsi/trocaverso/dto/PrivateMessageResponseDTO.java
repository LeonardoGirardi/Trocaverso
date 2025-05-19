package br.edu.bsi.trocaverso.dto;

import br.edu.bsi.trocaverso.model.PrivateMessage;

import java.time.LocalDate;
import java.util.UUID;

public class PrivateMessageResponseDTO {

    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private String content;
    private LocalDate date;

    public PrivateMessageResponseDTO(PrivateMessage message) {
        this.id = message.getId();
        this.senderId = message.getSender().getId();
        this.receiverId = message.getReceiver().getId();
        this.content = message.getContent();
        this.date = message.getDate();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
