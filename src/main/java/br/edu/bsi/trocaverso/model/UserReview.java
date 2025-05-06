package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_review")
public class UserReview extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewee_id", nullable = false)
    private User reviewee;

    @Enumerated
    @Column(name = "evaluation", nullable = false)
    private EvaluationReview evaluation;

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getReviewee() {
        return reviewee;
    }

    public void setReviewee(User reviewee) {
        this.reviewee = reviewee;
    }

    public EvaluationReview getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationReview evaluation) {
        this.evaluation = evaluation;
    }
}

