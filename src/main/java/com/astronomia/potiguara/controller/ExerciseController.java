package com.astronomia.potiguara.controller;

import com.astronomia.potiguara.dto.ValidateExerciseRequest;
import com.astronomia.potiguara.dto.ValidateExerciseResponse;
import com.astronomia.potiguara.model.Exercise;
import com.astronomia.potiguara.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercicios")
@Tag(name = "Exercícios", description = "Endpoints de Exercícios Interativos de Associação e Validação")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os exercícios disponíveis")
    public ResponseEntity<List<Exercise>> getAll() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @GetMapping("/{code}")
    @Operation(summary = "Obter dados de um exercício pelo código")
    public ResponseEntity<Exercise> getByCode(@PathVariable String code) {
        return exerciseService.getExerciseByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{code}/validar")
    @Operation(summary = "Validar respostas do exercício de ligar colunas e calcular XP")
    public ResponseEntity<ValidateExerciseResponse> validate(
            @PathVariable String code,
            @RequestBody ValidateExerciseRequest request) {
        return ResponseEntity.ok(exerciseService.validateExercise(code, request));
    }
}
