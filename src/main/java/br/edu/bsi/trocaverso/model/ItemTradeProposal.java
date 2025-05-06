package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_trade_proposal")

public class ItemTradeProposal extends GenericModel{

    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private User proposer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "item_offered_id")
    private Item itemOffered;

    @ManyToOne
    @JoinColumn(name = "item_requested_id")
    private Item itemRequested;

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

    public Item getItemOffered() {
        return itemOffered;
    }

    public void setItemOffered(Item itemOffered) {
        this.itemOffered = itemOffered;
    }

    public Item getItemRequested() {
        return itemRequested;
    }

    public void setItemRequested(Item itemRequested) {
        this.itemRequested = itemRequested;
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
