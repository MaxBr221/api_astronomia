package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "constellations")
public class Constellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String indigenousName;

    private String culture;

    private String subtitle;

    @Column(nullable = false)
    private String status; // LIBERADO, CONCLUIDO, BLOQUEADO, EM_PROGRESSO

    private String badgeLabel;

    private Integer progress;

    private String imageUrl;

    private String westernEquivalence;

    private String seasonalMeaning;

    private String observationSeason;

    private String region;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "constellation_badges", joinColumns = @JoinColumn(name = "constellation_id"))
    @Column(name = "badge")
    private List<String> badges = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String narrativeTupi;

    @Column(columnDefinition = "TEXT")
    private String narrativePortuguese;

    private String audioUrlTupi;

    private String audioUrlPortuguese;

    private Integer audioDurationSeconds;

    @OneToMany(mappedBy = "constellation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ConstellationStar> stars = new ArrayList<>();

    public Constellation() {
    }

    public Constellation(String code, String title, String indigenousName, String culture, String subtitle,
                         String status, String badgeLabel, Integer progress, String imageUrl,
                         String westernEquivalence, String seasonalMeaning, String observationSeason,
                         String region, List<String> badges, String narrativeTupi, String narrativePortuguese,
                         String audioUrlTupi, String audioUrlPortuguese, Integer audioDurationSeconds) {
        this.code = code;
        this.title = title;
        this.indigenousName = indigenousName;
        this.culture = culture;
        this.subtitle = subtitle;
        this.status = status;
        this.badgeLabel = badgeLabel;
        this.progress = progress;
        this.imageUrl = imageUrl;
        this.westernEquivalence = westernEquivalence;
        this.seasonalMeaning = seasonalMeaning;
        this.observationSeason = observationSeason;
        this.region = region;
        this.badges = badges != null ? badges : new ArrayList<>();
        this.narrativeTupi = narrativeTupi;
        this.narrativePortuguese = narrativePortuguese;
        this.audioUrlTupi = audioUrlTupi;
        this.audioUrlPortuguese = audioUrlPortuguese;
        this.audioDurationSeconds = audioDurationSeconds;
    }

    public void addStar(ConstellationStar star) {
        stars.add(star);
        star.setConstellation(this);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndigenousName() {
        return indigenousName;
    }

    public void setIndigenousName(String indigenousName) {
        this.indigenousName = indigenousName;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBadgeLabel() {
        return badgeLabel;
    }

    public void setBadgeLabel(String badgeLabel) {
        this.badgeLabel = badgeLabel;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWesternEquivalence() {
        return westernEquivalence;
    }

    public void setWesternEquivalence(String westernEquivalence) {
        this.westernEquivalence = westernEquivalence;
    }

    public String getSeasonalMeaning() {
        return seasonalMeaning;
    }

    public void setSeasonalMeaning(String seasonalMeaning) {
        this.seasonalMeaning = seasonalMeaning;
    }

    public String getObservationSeason() {
        return observationSeason;
    }

    public void setObservationSeason(String observationSeason) {
        this.observationSeason = observationSeason;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public String getNarrativeTupi() {
        return narrativeTupi;
    }

    public void setNarrativeTupi(String narrativeTupi) {
        this.narrativeTupi = narrativeTupi;
    }

    public String getNarrativePortuguese() {
        return narrativePortuguese;
    }

    public void setNarrativePortuguese(String narrativePortuguese) {
        this.narrativePortuguese = narrativePortuguese;
    }

    public String getAudioUrlTupi() {
        return audioUrlTupi;
    }

    public void setAudioUrlTupi(String audioUrlTupi) {
        this.audioUrlTupi = audioUrlTupi;
    }

    public String getAudioUrlPortuguese() {
        return audioUrlPortuguese;
    }

    public void setAudioUrlPortuguese(String audioUrlPortuguese) {
        this.audioUrlPortuguese = audioUrlPortuguese;
    }

    public Integer getAudioDurationSeconds() {
        return audioDurationSeconds;
    }

    public void setAudioDurationSeconds(Integer audioDurationSeconds) {
        this.audioDurationSeconds = audioDurationSeconds;
    }

    public List<ConstellationStar> getStars() {
        return stars;
    }

    public void setStars(List<ConstellationStar> stars) {
        this.stars = stars;
    }
}
