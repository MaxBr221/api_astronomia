package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_pairs")
public class ExercisePair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    @JsonBackReference
    private Exercise exercise;

    @Column(nullable = false)
    private String constellationKey; // e.g. "homem_velho"

    @Column(nullable = false)
    private String constellationLabel; // e.g. "O Homem Velho (Tuira)"

    private String constellationSubtitle; // e.g. "Tuira • Orion e Touro"

    private String constellationIcon; // e.g. "/assets/tuira_avatar.jpg"

    @Column(nullable = false)
    private String phenomenonKey; // e.g. "chuvas_milho"

    @Column(nullable = false)
    private String phenomenonLabel; // e.g. "Início das chuvas e época de plantar o milho"

    private String phenomenonSubtitle; // e.g. "Ciclo Agrícola • Verão do Nordeste"

    private String phenomenonIcon; // e.g. "/assets/rain_season.jpg"

    @Column(columnDefinition = "TEXT")
    private String explanation;

    public ExercisePair() {
    }

    public ExercisePair(String constellationKey, String constellationLabel, String constellationSubtitle,
                        String constellationIcon, String phenomenonKey, String phenomenonLabel,
                        String phenomenonSubtitle, String phenomenonIcon, String explanation) {
        this.constellationKey = constellationKey;
        this.constellationLabel = constellationLabel;
        this.constellationSubtitle = constellationSubtitle;
        this.constellationIcon = constellationIcon;
        this.phenomenonKey = phenomenonKey;
        this.phenomenonLabel = phenomenonLabel;
        this.phenomenonSubtitle = phenomenonSubtitle;
        this.phenomenonIcon = phenomenonIcon;
        this.explanation = explanation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getConstellationKey() {
        return constellationKey;
    }

    public void setConstellationKey(String constellationKey) {
        this.constellationKey = constellationKey;
    }

    public String getConstellationLabel() {
        return constellationLabel;
    }

    public void setConstellationLabel(String constellationLabel) {
        this.constellationLabel = constellationLabel;
    }

    public String getConstellationSubtitle() {
        return constellationSubtitle;
    }

    public void setConstellationSubtitle(String constellationSubtitle) {
        this.constellationSubtitle = constellationSubtitle;
    }

    public String getConstellationIcon() {
        return constellationIcon;
    }

    public void setConstellationIcon(String constellationIcon) {
        this.constellationIcon = constellationIcon;
    }

    public String getPhenomenonKey() {
        return phenomenonKey;
    }

    public void setPhenomenonKey(String phenomenonKey) {
        this.phenomenonKey = phenomenonKey;
    }

    public String getPhenomenonLabel() {
        return phenomenonLabel;
    }

    public void setPhenomenonLabel(String phenomenonLabel) {
        this.phenomenonLabel = phenomenonLabel;
    }

    public String getPhenomenonSubtitle() {
        return phenomenonSubtitle;
    }

    public void setPhenomenonSubtitle(String phenomenonSubtitle) {
        this.phenomenonSubtitle = phenomenonSubtitle;
    }

    public String getPhenomenonIcon() {
        return phenomenonIcon;
    }

    public void setPhenomenonIcon(String phenomenonIcon) {
        this.phenomenonIcon = phenomenonIcon;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
