package com.astronomia.potiguara.controller;

import com.astronomia.potiguara.model.Constellation;
import com.astronomia.potiguara.service.ConstellationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/constelacoes")
@Tag(name = "Constelações", description = "Endpoints da Trilha Celeste Potiguara e Fichas das Constelações")
public class ConstellationController {

    private final ConstellationService constellationService;

    public ConstellationController(ConstellationService constellationService) {
        this.constellationService = constellationService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as constelações da Trilha Potiguara")
    public ResponseEntity<List<Constellation>> getAll() {
        return ResponseEntity.ok(constellationService.getAllConstellations());
    }

    @GetMapping("/{code}")
    @Operation(summary = "Obter detalhes de uma constelação pelo código (ex: homem_velho, ema_celeste)")
    public ResponseEntity<Constellation> getByCode(@PathVariable String code) {
        return constellationService.getConstellationByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
