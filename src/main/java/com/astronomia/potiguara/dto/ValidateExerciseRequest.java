package com.astronomia.potiguara.dto;

import java.util.Map;

public class ValidateExerciseRequest {

    private Long userId;
    // Map of constellationKey -> submitted phenomenonKey
    private Map<String, String> answers;

    public ValidateExerciseRequest() {
    }

    public ValidateExerciseRequest(Long userId, Map<String, String> answers) {
        this.userId = userId;
        this.answers = answers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}
