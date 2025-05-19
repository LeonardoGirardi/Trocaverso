package br.edu.bsi.trocaverso.dto;

import java.util.UUID;

public class ItemTradeProposalRequestDTO {
    private UUID proposerId;
    private UUID receiverId;
    private UUID itemOfferedId;
    private UUID itemRequestedId;
    private UUID privateMessageId;
    private String status;

    public UUID getProposerId() {
        return proposerId;
    }

    public void setProposerId(UUID proposerId) {
        this.proposerId = proposerId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public UUID getItemOfferedId() {
        return itemOfferedId;
    }

    public void setItemOfferedId(UUID itemOfferedId) {
        this.itemOfferedId = itemOfferedId;
    }

    public UUID getItemRequestedId() {
        return itemRequestedId;
    }

    public void setItemRequestedId(UUID itemRequestedId) {
        this.itemRequestedId = itemRequestedId;
    }

    public UUID getPrivateMessageId() {
        return privateMessageId;
    }

    public void setPrivateMessageId(UUID privateMessageId) {
        this.privateMessageId = privateMessageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
