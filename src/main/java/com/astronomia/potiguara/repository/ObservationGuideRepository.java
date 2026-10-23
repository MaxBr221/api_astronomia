package com.astronomia.potiguara.repository;

import com.astronomia.potiguara.model.ObservationGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObservationGuideRepository extends JpaRepository<ObservationGuide, Long> {
    Optional<ObservationGuide> findByConstellationCode(String constellationCode);
}
