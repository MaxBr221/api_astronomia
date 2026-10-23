package com.astronomia.potiguara.service;

import com.astronomia.potiguara.model.Constellation;
import com.astronomia.potiguara.repository.ConstellationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ConstellationService {

    private final ConstellationRepository constellationRepository;

    public ConstellationService(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    public List<Constellation> getAllConstellations() {
        return constellationRepository.findAll();
    }

    public Optional<Constellation> getConstellationByCode(String code) {
        return constellationRepository.findByCode(code);
    }

    @Transactional
    public Constellation save(Constellation constellation) {
        return constellationRepository.save(constellation);
    }
}
