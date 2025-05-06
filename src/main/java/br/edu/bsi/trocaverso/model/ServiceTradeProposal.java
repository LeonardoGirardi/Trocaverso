package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_service_trade_proposal")
public class ServiceTradeProposal extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private User proposer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "service_offered_id")
    private ServiceOffer serviceOffered;

    @ManyToOne
    @JoinColumn(name = "service_requested_id")
    private ServiceOffer serviceRequested;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_message_id")
    private PrivateMessage privateMessage;

    @Enumerated(EnumType.STRING)
    private TradeProposalStatus status;

    public User getProposer() {
        return proposer;
    }

    public void setProposer(User proposer) {
        this.proposer = proposer;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public ServiceOffer getServiceOffered() {
        return serviceOffered;
    }

    public void setServiceOffered(ServiceOffer serviceOffered) {
        this.serviceOffered = serviceOffered;
    }

    public ServiceOffer getServiceRequested() {
        return serviceRequested;
    }

    public void setServiceRequested(ServiceOffer serviceRequested) {
        this.serviceRequested = serviceRequested;
    }

    public PrivateMessage getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(PrivateMessage privateMessage) {
        this.privateMessage = privateMessage;
    }

    public TradeProposalStatus getStatus() {
        return status;
    }

    public void setStatus(TradeProposalStatus status) {
        this.status = status;
    }
}
