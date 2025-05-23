package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
