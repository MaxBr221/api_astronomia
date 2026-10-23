package com.astronomia.potiguara.service;

import com.astronomia.potiguara.dto.AuthResponse;
import com.astronomia.potiguara.dto.LoginRequest;
import com.astronomia.potiguara.dto.RegisterRequest;
import com.astronomia.potiguara.dto.UpdateProgressRequest;
import com.astronomia.potiguara.model.UserProfile;
import com.astronomia.potiguara.model.UserProgress;
import com.astronomia.potiguara.repository.UserProfileRepository;
import com.astronomia.potiguara.repository.UserProgressRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserProfileRepository userProfileRepository;
    private final UserProgressRepository userProgressRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserProfileRepository userProfileRepository,
                       UserProgressRepository userProgressRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userProgressRepository = userProgressRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        String cleanUsername = request.getUsername().trim();
        String cleanEmail = request.getEmail().trim().toLowerCase();

        if (userProfileRepository.existsByUsernameIgnoreCase(cleanUsername)) {
            throw new IllegalArgumentException("Nome de usuário já está em uso: @" + cleanUsername);
        }

        if (userProfileRepository.existsByEmailIgnoreCase(cleanEmail)) {
            throw new IllegalArgumentException("E-mail já cadastrado: " + cleanEmail);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        String avatar = (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank())
                ? request.getAvatarUrl()
                : "/assets/tuira_avatar.jpg";
        String fullName = (request.getFullName() != null && !request.getFullName().isBlank())
                ? request.getFullName()
                : cleanUsername;

        UserProfile user = new UserProfile(
                cleanUsername,
                cleanEmail,
                encodedPassword,
                fullName,
                avatar,
                "Explorador Iniciante • Nível 1",
                0,
                500,
                3,
                3,
                "Lua Nova (Jaci) • Céu Limpo"
        );

        // Inicializa a trilha astronômica para o novo explorador
        user.addProgress(new UserProgress("homem_velho", "LIBERADO", 0, false, false, false, false));
        user.addProgress(new UserProgress("ema_celeste", "BLOQUEADO", 0, false, false, false, false));
        user.addProgress(new UserProgress("caminho_antas", "BLOQUEADO", 0, false, false, false, false));
        user.addProgress(new UserProgress("veado", "BLOQUEADO", 0, false, false, false, false));

        UserProfile saved = userProfileRepository.save(user);
        String token = "potiguara-auth-" + UUID.randomUUID();

        return AuthResponse.success("Cadastro realizado com sucesso! Bem-vindo(a) à Trilha Celeste Potiguara.", token, saved);
    }

    public AuthResponse login(LoginRequest request) {
        String identifier = request.getIdentifier().trim();
        UserProfile user = userProfileRepository.findByEmailIgnoreCaseOrUsernameIgnoreCase(identifier, identifier)
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas: usuário ou e-mail não encontrado."));

        if (user.getPassword() != null && !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas: senha incorreta.");
        }

        String token = "potiguara-auth-" + UUID.randomUUID();
        return AuthResponse.success("Login efetuado com sucesso!", token, user);
    }

    @Transactional(readOnly = true)
    public Optional<UserProfile> getDefaultUser() {
        return userProfileRepository.findAll().stream().findFirst();
    }

    @Transactional(readOnly = true)
    public Optional<UserProfile> getUserById(Long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile updateProgress(Long userId, UpdateProgressRequest request) {
        UserProfile user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));

        UserProgress nodeProgress = userProgressRepository
                .findByUserIdAndConstellationCode(userId, request.getConstellationCode())
                .orElseGet(() -> {
                    UserProgress newProgress = new UserProgress();
                    newProgress.setUser(user);
                    newProgress.setConstellationCode(request.getConstellationCode());
                    newProgress.setStatus("EM_PROGRESSO");
                    newProgress.setProgress(0);
                    return newProgress;
                });

        if (request.getAudioCompleted() != null) {
            nodeProgress.setAudioCompleted(request.getAudioCompleted());
        }
        if (request.getLessonCompleted() != null) {
            nodeProgress.setLessonCompleted(request.getLessonCompleted());
        }
        if (request.getStellariumCompleted() != null) {
            nodeProgress.setStellariumCompleted(request.getStellariumCompleted());
        }
        if (request.getExerciseCompleted() != null) {
            nodeProgress.setExerciseCompleted(request.getExerciseCompleted());
        }

        // Calcular percentual baseado nos 4 pilares
        int count = 0;
        if (Boolean.TRUE.equals(nodeProgress.getAudioCompleted())) count++;
        if (Boolean.TRUE.equals(nodeProgress.getLessonCompleted())) count++;
        if (Boolean.TRUE.equals(nodeProgress.getStellariumCompleted())) count++;
        if (Boolean.TRUE.equals(nodeProgress.getExerciseCompleted())) count++;

        int percentage = count * 25;
        nodeProgress.setProgress(percentage);

        if (percentage >= 100) {
            nodeProgress.setStatus("CONCLUIDO");
        } else if (percentage > 0) {
            nodeProgress.setStatus("EM_PROGRESSO");
        }

        userProgressRepository.save(nodeProgress);
        return userProfileRepository.findById(userId).orElse(user);
    }

    public UserProfile resetLives(Long userId) {
        UserProfile user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));
        user.setLives(user.getMaxLives());
        return userProfileRepository.save(user);
    }
}
