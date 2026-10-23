package com.astronomia.potiguara.controller;

import com.astronomia.potiguara.dto.AuthResponse;
import com.astronomia.potiguara.dto.LoginRequest;
import com.astronomia.potiguara.dto.RegisterRequest;
import com.astronomia.potiguara.model.UserProfile;
import com.astronomia.potiguara.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints de login, cadastro e verificação de sessão do explorador")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastro")
    @Operation(summary = "Cadastrar novo explorador astronômico")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthResponse.failure(e.getMessage()));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar explorador (por e-mail ou nome de usuário e senha)")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponse.failure(e.getMessage()));
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Obter dados do usuário ativo da sessão")
    public ResponseEntity<UserProfile> getCurrentUser(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return userService.getUserById(userId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        return userService.getDefaultUser()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
