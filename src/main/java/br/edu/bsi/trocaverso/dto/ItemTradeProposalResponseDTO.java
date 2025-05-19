package br.edu.bsi.trocaverso.dto;

import br.edu.bsi.trocaverso.model.ItemTradeProposal;

import java.util.UUID;

public class ItemTradeProposalResponseDTO {
    private UUID id;
    private UUID proposerId;
    private UUID receiverId;
    private UUID itemOfferedId;
    private UUID itemRequestedId;
    private UUID privateMessageId;
    private String status;

    public ItemTradeProposalResponseDTO(ItemTradeProposal proposal) {
        this.id = id;
        this.proposerId = proposerId;
        this.receiverId = receiverId;
        this.itemOfferedId = itemOfferedId;
        this.privateMessageId = privateMessageId;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
