package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_review")
public class UserReview extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private UserProfile reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewee_id", nullable = false)
    private UserProfile reviewee;

    @Enumerated
    @Column(name = "evaluation", nullable = false)
    private EvaluationReview evaluation;

    public UserProfile getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserProfile reviewer) {
        this.reviewer = reviewer;
    }

    public UserProfile getReviewee() {
        return reviewee;
    }

    public void setReviewee(UserProfile reviewee) {
        this.reviewee = reviewee;
    }

    public EvaluationReview getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationReview evaluation) {
        this.evaluation = evaluation;
    }
}

