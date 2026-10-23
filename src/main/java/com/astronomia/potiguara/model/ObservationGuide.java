package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "observation_guides")
public class ObservationGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String constellationCode;

    @Column(nullable = false)
    private String constellationTitle;

    private Double azimuth; // e.g. 78.5

    private Double altitude; // e.g. 42.0

    private String bestTime; // e.g. "21:30 (Horário de Brasília)"

    private String cardinalDirection; // e.g. "Leste (E) - Nascente"

    private String locationName; // e.g. "Baía da Traição - PB"

    private Double latitude; // -6.68

    private Double longitude; // -34.93

    private String fov; // e.g. "60°"

    private String recommendedDate; // e.g. "15 de Janeiro"

    @Column(columnDefinition = "TEXT")
    private String observationalNotes;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("stepNumber ASC")
    @JsonManagedReference
    private List<ObservationStep> steps = new ArrayList<>();

    public ObservationGuide() {
    }

    public ObservationGuide(String constellationCode, String constellationTitle, Double azimuth,
                            Double altitude, String bestTime, String cardinalDirection, String locationName,
                            Double latitude, Double longitude, String fov, String recommendedDate,
                            String observationalNotes) {
        this.constellationCode = constellationCode;
        this.constellationTitle = constellationTitle;
        this.azimuth = azimuth;
        this.altitude = altitude;
        this.bestTime = bestTime;
        this.cardinalDirection = cardinalDirection;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fov = fov;
        this.recommendedDate = recommendedDate;
        this.observationalNotes = observationalNotes;
    }

    public void addStep(ObservationStep step) {
        steps.add(step);
        step.setGuide(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConstellationCode() {
        return constellationCode;
    }

    public void setConstellationCode(String constellationCode) {
        this.constellationCode = constellationCode;
    }

    public String getConstellationTitle() {
        return constellationTitle;
    }

    public void setConstellationTitle(String constellationTitle) {
        this.constellationTitle = constellationTitle;
    }

    public Double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public String getCardinalDirection() {
        return cardinalDirection;
    }

    public void setCardinalDirection(String cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getFov() {
        return fov;
    }

    public void setFov(String fov) {
        this.fov = fov;
    }

    public String getRecommendedDate() {
        return recommendedDate;
    }

    public void setRecommendedDate(String recommendedDate) {
        this.recommendedDate = recommendedDate;
    }

    public String getObservationalNotes() {
        return observationalNotes;
    }

    public void setObservationalNotes(String observationalNotes) {
        this.observationalNotes = observationalNotes;
    }

    public List<ObservationStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ObservationStep> steps) {
        this.steps = steps;
    }
}
