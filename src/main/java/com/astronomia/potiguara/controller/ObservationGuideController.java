package com.astronomia.potiguara.controller;

import com.astronomia.potiguara.model.ObservationGuide;
import com.astronomia.potiguara.service.ObservationGuideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observatorio")
@Tag(name = "Observatório & Stellarium", description = "Endpoints de Guias de Observação e Coordenadas Stellarium")
public class ObservationGuideController {

    private final ObservationGuideService guideService;

    public ObservationGuideController(ObservationGuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os guias de observação")
    public ResponseEntity<List<ObservationGuide>> getAll() {
        return ResponseEntity.ok(guideService.getAllGuides());
    }

    @GetMapping("/{constellationCode}")
    @Operation(summary = "Obter guia de observação e passos do Stellarium para uma constelação")
    public ResponseEntity<ObservationGuide> getByCode(@PathVariable String constellationCode) {
        return guideService.getGuideByConstellationCode(constellationCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
