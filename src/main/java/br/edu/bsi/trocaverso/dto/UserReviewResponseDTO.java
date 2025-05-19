package br.edu.bsi.trocaverso.dto;

import br.edu.bsi.trocaverso.model.UserReview;

import java.util.UUID;

public class UserReviewResponseDTO {
    private UUID id;
    private UUID reviewerId;
    private UUID revieweeId;
    private String evaluation;

    public UserReviewResponseDTO(UserReview userReview) {
        this.id = id;
        this.reviewerId = reviewerId;
        this.revieweeId = revieweeId;
        this.evaluation = evaluation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(UUID reviewerId) {
        this.reviewerId = reviewerId;
    }

    public UUID getRevieweeId() {
        return revieweeId;
    }

    public void setRevieweeId(UUID revieweeId) {
        this.revieweeId = revieweeId;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
