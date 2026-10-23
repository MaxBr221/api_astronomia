package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String fullName;

    private String avatarUrl;

    private String level;

    private Integer xp;

    private Integer maxXp;

    private Integer lives;

    private Integer maxLives;

    private String lunarCycle;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<UserProgress> progressList = new ArrayList<>();

    public UserProfile() {
    }

    public UserProfile(String username, String email, String password, String fullName, String avatarUrl, String level,
                       Integer xp, Integer maxXp, Integer lives, Integer maxLives, String lunarCycle) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.level = level;
        this.xp = xp;
        this.maxXp = maxXp;
        this.lives = lives;
        this.maxLives = maxLives;
        this.lunarCycle = lunarCycle;
    }

    public UserProfile(String username, String fullName, String avatarUrl, String level,
                       Integer xp, Integer maxXp, Integer lives, Integer maxLives, String lunarCycle) {
        this(username, null, null, fullName, avatarUrl, level, xp, maxXp, lives, maxLives, lunarCycle);
    }

    public void addProgress(UserProgress progress) {
        progressList.add(progress);
        progress.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getMaxXp() {
        return maxXp;
    }

    public void setMaxXp(Integer maxXp) {
        this.maxXp = maxXp;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }

    public Integer getMaxLives() {
        return maxLives;
    }

    public void setMaxLives(Integer maxLives) {
        this.maxLives = maxLives;
    }

    public String getLunarCycle() {
        return lunarCycle;
    }

    public void setLunarCycle(String lunarCycle) {
        this.lunarCycle = lunarCycle;
    }

    public List<UserProgress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<UserProgress> progressList) {
        this.progressList = progressList;
    }
}
