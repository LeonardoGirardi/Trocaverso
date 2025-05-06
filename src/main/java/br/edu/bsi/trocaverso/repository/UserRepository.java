package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
