package com.astronomia.potiguara.service;

import com.astronomia.potiguara.dto.PairValidationResult;
import com.astronomia.potiguara.dto.ValidateExerciseRequest;
import com.astronomia.potiguara.dto.ValidateExerciseResponse;
import com.astronomia.potiguara.model.Exercise;
import com.astronomia.potiguara.model.ExercisePair;
import com.astronomia.potiguara.model.UserProfile;
import com.astronomia.potiguara.model.UserProgress;
import com.astronomia.potiguara.repository.ExerciseRepository;
import com.astronomia.potiguara.repository.UserProfileRepository;
import com.astronomia.potiguara.repository.UserProgressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserProgressRepository userProgressRepository;

    public ExerciseService(ExerciseRepository exerciseRepository,
                           UserProfileRepository userProfileRepository,
                           UserProgressRepository userProgressRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userProfileRepository = userProfileRepository;
        this.userProgressRepository = userProgressRepository;
    }

    @Transactional(readOnly = true)
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Exercise> getExerciseByCode(String code) {
        return exerciseRepository.findByCode(code);
    }

    public ValidateExerciseResponse validateExercise(String code, ValidateExerciseRequest request) {
        Exercise exercise = exerciseRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Exercício não encontrado com o código: " + code));

        Map<String, String> submittedAnswers = request.getAnswers();
        List<PairValidationResult> details = new ArrayList<>();
        int correctCount = 0;
        int totalPairs = exercise.getPairs().size();

        for (ExercisePair pair : exercise.getPairs()) {
            String constellationKey = pair.getConstellationKey();
            String correctPhenomenon = pair.getPhenomenonKey();
            String submittedPhenomenon = submittedAnswers != null ? submittedAnswers.get(constellationKey) : null;

            boolean isCorrect = correctPhenomenon != null && correctPhenomenon.equalsIgnoreCase(submittedPhenomenon);
            if (isCorrect) {
                correctCount++;
            }

            details.add(new PairValidationResult(
                    constellationKey,
                    submittedPhenomenon,
                    correctPhenomenon,
                    isCorrect,
                    pair.getExplanation()
            ));
        }

        boolean allCorrect = (correctCount == totalPairs && totalPairs > 0);
        int xpEarned = allCorrect ? exercise.getRewardXp() : (correctCount * 30);
        int score = totalPairs > 0 ? (int) Math.round(((double) correctCount / totalPairs) * 100) : 0;

        String feedback = allCorrect
                ? "Excelente! Você compreendeu perfeitamente a sabedoria ancestral Potiguara e a relação entre as estrelas e a natureza."
                : "Você acertou " + correctCount + " de " + totalPairs + " pares. Reveja a posição de Tuira e da Ema no céu para tentar novamente!";

        // Se houver usuário, atualizar pontuação, vidas e desbloqueios
        if (request.getUserId() != null) {
            userProfileRepository.findById(request.getUserId()).ifPresent(user -> {
                user.setXp(user.getXp() + xpEarned);

                if (!allCorrect && user.getLives() != null && user.getLives() > 0) {
                    user.setLives(Math.max(0, user.getLives() - 1));
                }

                // Atualizar progresso do Homem Velho
                userProgressRepository.findByUserIdAndConstellationCode(user.getId(), "homem_velho")
                        .ifPresent(progress -> {
                            if (allCorrect) {
                                progress.setExerciseCompleted(true);
                                progress.setStatus("CONCLUIDO");
                                progress.setProgress(100);
                            }
                            userProgressRepository.save(progress);
                        });

                userProfileRepository.save(user);
            });
        }

        return new ValidateExerciseResponse(
                allCorrect,
                correctCount,
                totalPairs,
                score,
                xpEarned,
                feedback,
                details
        );
    }
}
