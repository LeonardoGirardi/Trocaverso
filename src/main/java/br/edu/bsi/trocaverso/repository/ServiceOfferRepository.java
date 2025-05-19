package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, UUID> {

}
