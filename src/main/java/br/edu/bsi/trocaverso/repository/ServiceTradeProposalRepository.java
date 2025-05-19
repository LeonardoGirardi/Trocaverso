package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.ServiceTradeProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceTradeProposalRepository extends JpaRepository<ServiceTradeProposal, UUID> {
}
