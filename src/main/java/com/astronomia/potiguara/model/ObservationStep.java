package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "observation_steps")
public class ObservationStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id", nullable = false)
    @JsonBackReference
    private ObservationGuide guide;

    @Column(nullable = false)
    private Integer stepNumber;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private String stellariumAction; // e.g. "SET_TIME_2130", "LOOK_EAST_AZ78"

    private String toolTip;

    public ObservationStep() {
    }

    public ObservationStep(Integer stepNumber, String title, String description, String stellariumAction, String toolTip) {
        this.stepNumber = stepNumber;
        this.title = title;
        this.description = description;
        this.stellariumAction = stellariumAction;
        this.toolTip = toolTip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObservationGuide getGuide() {
        return guide;
    }

    public void setGuide(ObservationGuide guide) {
        this.guide = guide;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStellariumAction() {
        return stellariumAction;
    }

    public void setStellariumAction(String stellariumAction) {
        this.stellariumAction = stellariumAction;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }
}
