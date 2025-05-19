package br.edu.bsi.trocaverso.dto;

import java.util.UUID;

public class ServiceTradeProposalRequestDTO {
    private UUID proposerId;
    private UUID receiverId;
    private UUID serviceOfferedId;
    private UUID serviceRequestedId;
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

    public UUID getServiceOfferedId() {
        return serviceOfferedId;
    }

    public void setServiceOfferedId(UUID serviceOfferedId) {
        this.serviceOfferedId = serviceOfferedId;
    }

    public UUID getServiceRequestedId() {
        return serviceRequestedId;
    }

    public void setServiceRequestedId(UUID serviceRequestedId) {
        this.serviceRequestedId = serviceRequestedId;
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
