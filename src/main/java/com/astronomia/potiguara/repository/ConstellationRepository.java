package com.astronomia.potiguara.repository;

import com.astronomia.potiguara.model.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    Optional<Constellation> findByCode(String code);
}
