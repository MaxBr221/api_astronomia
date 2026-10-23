package com.astronomia.potiguara.controller;

import com.astronomia.potiguara.dto.UpdateProgressRequest;
import com.astronomia.potiguara.model.UserProfile;
import com.astronomia.potiguara.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários & Progresso", description = "Endpoints de perfil do usuário, nível, XP e progresso na trilha")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    @Operation(summary = "Obter o perfil ativo do estudante/explorador")
    public ResponseEntity<UserProfile> getDefaultProfile() {
        return userService.getDefaultUser()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter perfil do usuário por ID")
    public ResponseEntity<UserProfile> getById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/progresso")
    @Operation(summary = "Atualizar progresso de uma constelação (áudio, lição, stellarium, exercício)")
    public ResponseEntity<UserProfile> updateProgress(
            @PathVariable Long id,
            @RequestBody UpdateProgressRequest request) {
        return ResponseEntity.ok(userService.updateProgress(id, request));
    }

    @PostMapping("/{id}/recuperar-vidas")
    @Operation(summary = "Recuperar as vidas do explorador")
    public ResponseEntity<UserProfile> resetLives(@PathVariable Long id) {
        return ResponseEntity.ok(userService.resetLives(id));
    }
}
