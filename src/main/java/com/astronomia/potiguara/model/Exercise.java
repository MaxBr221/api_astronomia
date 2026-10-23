package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String code;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    private Integer rewardXp;

    private Integer totalPairs;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ExercisePair> pairs = new ArrayList<>();

    public Exercise() {
    }

    public Exercise(String code, String title, String subtitle, String instructions, Integer rewardXp, Integer totalPairs) {
        this.code = code;
        this.title = title;
        this.subtitle = subtitle;
        this.instructions = instructions;
        this.rewardXp = rewardXp;
        this.totalPairs = totalPairs;
    }

    public void addPair(ExercisePair pair) {
        pairs.add(pair);
        pair.setExercise(this);
    }

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getRewardXp() {
        return rewardXp;
    }

    public void setRewardXp(Integer rewardXp) {
        this.rewardXp = rewardXp;
    }

    public Integer getTotalPairs() {
        return totalPairs;
    }

    public void setTotalPairs(Integer totalPairs) {
        this.totalPairs = totalPairs;
    }

    public List<ExercisePair> getPairs() {
        return pairs;
    }

    public void setPairs(List<ExercisePair> pairs) {
        this.pairs = pairs;
    }
}
