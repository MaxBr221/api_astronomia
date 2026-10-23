package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "user_node_progress")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserProfile user;

    @Column(nullable = false)
    private String constellationCode; // e.g. "homem_velho"

    @Column(nullable = false)
    private String status; // LIBERADO, EM_PROGRESSO, CONCLUIDO, BLOQUEADO

    private Integer progress; // percentage (0 - 100)

    private Boolean audioCompleted = false;

    private Boolean lessonCompleted = false;

    private Boolean stellariumCompleted = false;

    private Boolean exerciseCompleted = false;

    public UserProgress() {
    }

    public UserProgress(String constellationCode, String status, Integer progress,
                        Boolean audioCompleted, Boolean lessonCompleted,
                        Boolean stellariumCompleted, Boolean exerciseCompleted) {
        this.constellationCode = constellationCode;
        this.status = status;
        this.progress = progress;
        this.audioCompleted = audioCompleted;
        this.lessonCompleted = lessonCompleted;
        this.stellariumCompleted = stellariumCompleted;
        this.exerciseCompleted = exerciseCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public String getConstellationCode() {
        return constellationCode;
    }

    public void setConstellationCode(String constellationCode) {
        this.constellationCode = constellationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
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
