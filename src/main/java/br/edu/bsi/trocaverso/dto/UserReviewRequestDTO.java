package br.edu.bsi.trocaverso.dto;

import br.edu.bsi.trocaverso.model.UserReview;

import java.util.UUID;

public class UserReviewRequestDTO {
    private UUID reviewerId;
    private UUID revieweeId;
    private String evaluation;

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
