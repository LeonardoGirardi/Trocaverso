package br.edu.bsi.trocaverso.model;

public class TradeProposal extends GenericModel{
    private User proposer;
    private User receiver;
    private Item itemOffered;
    private Item itemRequested;
    private ServiceOffer serviceOffered;
    private ServiceOffer serviceRequested;
    private PrivateMessage privateMessage;
    private TradeProposalStatus status;
}
