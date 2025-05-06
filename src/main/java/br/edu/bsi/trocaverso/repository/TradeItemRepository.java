package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TradeItemRepository extends JpaRepository<Item, UUID> {
    // Custom query methods can be defined here if needed
    // For example, findByName(String name) or findByCategory(String category)
}
