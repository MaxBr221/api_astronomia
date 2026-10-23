package com.astronomia.potiguara.dto;

import java.util.List;

public class ValidateExerciseResponse {

    private boolean allCorrect;
    private int correctCount;
    private int totalCount;
    private int score;
    private int xpEarned;
    private String feedback;
    private List<PairValidationResult> details;

    public ValidateExerciseResponse() {
    }

    public ValidateExerciseResponse(boolean allCorrect, int correctCount, int totalCount,
                                    int score, int xpEarned, String feedback,
                                    List<PairValidationResult> details) {
        this.allCorrect = allCorrect;
        this.correctCount = correctCount;
        this.totalCount = totalCount;
        this.score = score;
        this.xpEarned = xpEarned;
        this.feedback = feedback;
        this.details = details;
    }

    public boolean isAllCorrect() {
        return allCorrect;
    }

    public void setAllCorrect(boolean allCorrect) {
        this.allCorrect = allCorrect;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getXpEarned() {
        return xpEarned;
    }

    public void setXpEarned(int xpEarned) {
        this.xpEarned = xpEarned;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<PairValidationResult> getDetails() {
        return details;
    }

    public void setDetails(List<PairValidationResult> details) {
        this.details = details;
    }
}
