package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserReviewService {

    private final UserProfileRepository userProfileRepository;
    private final UserReviewRepository userReviewRepository;

    public UserReviewService(UserProfileRepository userProfileRepository, UserReviewRepository userReviewRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userReviewRepository = userReviewRepository;
    }

    public UserReviewResponseDTO create(UserReviewRequestDTO dto) {
        UserProfile reviewer = userProfileRepository.findById(dto.getReviewerId()).orElse(null);
        UserProfile reviewee = userProfileRepository.findById(dto.getRevieweeId()).orElse(null);

        if (reviewer == null || reviewee == null) return null;

        UserReview review = new UserReview();
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setEvaluation(review.getEvaluation());
        review = userReviewRepository.save(review);

        return toDTO(review);
    }

    public List<UserReviewResponseDTO> findAll() {
        return userReviewRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public UserReviewResponseDTO findById(UUID id) {
        return userReviewRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public UserReviewResponseDTO update(UUID id, UserReviewRequestDTO dto) {
        UserReview review = userReviewRepository.findById(id).orElse(null);
        if (review == null) return null;

        UserProfile reviewer = userProfileRepository.findById(dto.getReviewerId()).orElse(null);
        UserProfile reviewee = userProfileRepository.findById(dto.getRevieweeId()).orElse(null);

        if (reviewer == null || reviewee == null) return null;

        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setEvaluation(review.getEvaluation());
        review = userReviewRepository.save(review);

        return toDTO(review);
    }

    public void delete(UUID id) {
        userReviewRepository.deleteById(id);
    }

    private UserReviewResponseDTO toDTO(UserReview review) {
        UserReviewResponseDTO dto = new UserReviewResponseDTO(review);
        dto.setId(review.getId());
        dto.setReviewerId(review.getReviewer().getId());
        dto.setRevieweeId(review.getReviewee().getId());
        dto.setEvaluation(review.getEvaluation().toString());
        return dto;
    }

    public List<UserReviewResponseDTO> findByUserId(UUID userId) {
        List<UserReview> reviews = userReviewRepository.findByReviewerId(userId);
        return reviews.stream()
                .map(UserReviewResponseDTO::new)
                .collect(Collectors.toList());
    }
}