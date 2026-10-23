package com.astronomia.potiguara.repository;

import com.astronomia.potiguara.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    Optional<UserProgress> findByUserIdAndConstellationCode(Long userId, String constellationCode);
}
