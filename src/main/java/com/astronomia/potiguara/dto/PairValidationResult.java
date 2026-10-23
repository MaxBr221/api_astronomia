package com.astronomia.potiguara.dto;

public class PairValidationResult {

    private String constellationKey;
    private String submittedPhenomenonKey;
    private String correctPhenomenonKey;
    private boolean correct;
    private String explanation;

    public PairValidationResult() {
    }

    public PairValidationResult(String constellationKey, String submittedPhenomenonKey, String correctPhenomenonKey, boolean correct, String explanation) {
        this.constellationKey = constellationKey;
        this.submittedPhenomenonKey = submittedPhenomenonKey;
        this.correctPhenomenonKey = correctPhenomenonKey;
        this.correct = correct;
        this.explanation = explanation;
    }

    public String getConstellationKey() {
        return constellationKey;
    }

    public void setConstellationKey(String constellationKey) {
        this.constellationKey = constellationKey;
    }

    public String getSubmittedPhenomenonKey() {
        return submittedPhenomenonKey;
    }

    public void setSubmittedPhenomenonKey(String submittedPhenomenonKey) {
        this.submittedPhenomenonKey = submittedPhenomenonKey;
    }

    public String getCorrectPhenomenonKey() {
        return correctPhenomenonKey;
    }

    public void setCorrectPhenomenonKey(String correctPhenomenonKey) {
        this.correctPhenomenonKey = correctPhenomenonKey;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
