package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
