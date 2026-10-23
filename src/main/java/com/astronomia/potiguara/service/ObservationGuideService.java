package com.astronomia.potiguara.service;

import com.astronomia.potiguara.model.ObservationGuide;
import com.astronomia.potiguara.repository.ObservationGuideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ObservationGuideService {

    private final ObservationGuideRepository guideRepository;

    public ObservationGuideService(ObservationGuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public List<ObservationGuide> getAllGuides() {
        return guideRepository.findAll();
    }

    public Optional<ObservationGuide> getGuideByConstellationCode(String constellationCode) {
        return guideRepository.findByConstellationCode(constellationCode);
    }

    @Transactional
    public ObservationGuide save(ObservationGuide guide) {
        return guideRepository.save(guide);
    }
}
