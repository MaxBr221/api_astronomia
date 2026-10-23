package com.astronomia.potiguara.dto;

public class UpdateProgressRequest {

    private String constellationCode;
    private Boolean audioCompleted;
    private Boolean lessonCompleted;
    private Boolean stellariumCompleted;
    private Boolean exerciseCompleted;

    public UpdateProgressRequest() {
    }

    public UpdateProgressRequest(String constellationCode, Boolean audioCompleted, Boolean lessonCompleted, Boolean stellariumCompleted, Boolean exerciseCompleted) {
        this.constellationCode = constellationCode;
        this.audioCompleted = audioCompleted;
        this.lessonCompleted = lessonCompleted;
        this.stellariumCompleted = stellariumCompleted;
        this.exerciseCompleted = exerciseCompleted;
    }

    public String getConstellationCode() {
        return constellationCode;
    }

    public void setConstellationCode(String constellationCode) {
        this.constellationCode = constellationCode;
    }

    public Boolean getAudioCompleted() {
        return audioCompleted;
    }

    public void setAudioCompleted(Boolean audioCompleted) {
        this.audioCompleted = audioCompleted;
    }

    public Boolean getLessonCompleted() {
        return lessonCompleted;
    }

    public void setLessonCompleted(Boolean lessonCompleted) {
        this.lessonCompleted = lessonCompleted;
    }

    public Boolean getStellariumCompleted() {
        return stellariumCompleted;
    }

    public void setStellariumCompleted(Boolean stellariumCompleted) {
        this.stellariumCompleted = stellariumCompleted;
    }

    public Boolean getExerciseCompleted() {
        return exerciseCompleted;
    }

    public void setExerciseCompleted(Boolean exerciseCompleted) {
        this.exerciseCompleted = exerciseCompleted;
    }
}
