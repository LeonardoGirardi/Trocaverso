package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserReviewRepository extends JpaRepository<UserReview, UUID> {
    List<UserReview> findByReviewerId(UUID userId);
}
