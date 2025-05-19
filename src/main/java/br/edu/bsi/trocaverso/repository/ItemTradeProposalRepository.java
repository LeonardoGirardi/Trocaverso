package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.ItemTradeProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemTradeProposalRepository extends JpaRepository<ItemTradeProposal, UUID> {
}
