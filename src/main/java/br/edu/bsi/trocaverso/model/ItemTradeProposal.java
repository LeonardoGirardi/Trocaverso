package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_trade_proposal")

public class ItemTradeProposal extends GenericModel{

    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private UserProfile proposer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserProfile receiver;

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

    public UserProfile getProposer() {
        return proposer;
    }

    public void setProposer(UserProfile proposer) {
        this.proposer = proposer;
    }

    public UserProfile getReceiver() {
        return receiver;
    }

    public void setReceiver(UserProfile receiver) {
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
