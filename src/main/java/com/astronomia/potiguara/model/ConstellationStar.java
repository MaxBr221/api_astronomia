package com.astronomia.potiguara.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "constellation_stars")
public class ConstellationStar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constellation_id", nullable = false)
    @JsonBackReference
    private Constellation constellation;

    @Column(nullable = false)
    private String indigenousPart; // e.g. "Cabeça de Tuira", "Braço Direito", "Cajado"

    @Column(nullable = false)
    private String westernStar; // e.g. "Betelgeuse (Alpha Orionis)"

    private String astronomicalMeaning; // e.g. "Supergigante vermelha, olho brilhante de Tuira"

    private Double apparentMagnitude;

    public ConstellationStar() {
    }

    public ConstellationStar(String indigenousPart, String westernStar, String astronomicalMeaning, Double apparentMagnitude) {
        this.indigenousPart = indigenousPart;
        this.westernStar = westernStar;
        this.astronomicalMeaning = astronomicalMeaning;
        this.apparentMagnitude = apparentMagnitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public String getIndigenousPart() {
        return indigenousPart;
    }

    public void setIndigenousPart(String indigenousPart) {
        this.indigenousPart = indigenousPart;
    }

    public String getWesternStar() {
        return westernStar;
    }

    public void setWesternStar(String westernStar) {
        this.westernStar = westernStar;
    }

    public String getAstronomicalMeaning() {
        return astronomicalMeaning;
    }

    public void setAstronomicalMeaning(String astronomicalMeaning) {
        this.astronomicalMeaning = astronomicalMeaning;
    }

    public Double getApparentMagnitude() {
        return apparentMagnitude;
    }

    public void setApparentMagnitude(Double apparentMagnitude) {
        this.apparentMagnitude = apparentMagnitude;
    }
}
