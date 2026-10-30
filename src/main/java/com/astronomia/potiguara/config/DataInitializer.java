package com.astronomia.potiguara.config;

import com.astronomia.potiguara.model.*;
import com.astronomia.potiguara.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final ConstellationRepository constellationRepository;
    private final ObservationGuideRepository guideRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserProfileRepository userProfileRepository;

    public DataInitializer(ConstellationRepository constellationRepository,
                           ObservationGuideRepository guideRepository,
                           ExerciseRepository exerciseRepository,
                           UserProfileRepository userProfileRepository) {
        this.constellationRepository = constellationRepository;
        this.guideRepository = guideRepository;
        this.exerciseRepository = exerciseRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void run(String... args) {
        if (constellationRepository.count() == 0) {
            initConstellations();
        }

        if (guideRepository.count() == 0) {
            initObservationGuides();
        }

        if (exerciseRepository.count() == 0) {
            initExercises();
        }

        if (userProfileRepository.count() == 0) {
            initUserProfile();
        }
    }

    private void initConstellations() {
        // 1. O Homem Velho (Tuira)
        Constellation tuira = new Constellation(
                "homem_velho",
                "HOMEM VELHO",
                "Tuira",
                "Potiguara / Tupi-Guarani",
                "A Criação & Ciclos da Natureza",
                "LIBERADO",
                "LIBERADO",
                85,
                "/assets/tuira_orion.jpg",
                "Orion e Touro",
                "Início das chuvas e época de plantar o milho",
                "Dezembro a Março",
                "Baía da Traição, Litoral Norte da Paraíba",
                Arrays.asList("Áudio do Ancião 🎧", "Lição Sagrada 📖", "Stellarium 🔭", "Exercício ✍️"),
                "Tuira tuwixaba ybyrapytá rupí, amana oky oje'ea...", // Narrativa Tupi
                "Segundo os sábios e pajés da tradição Potiguara e Tupi-Guarani, o Homem Velho (Tuira) representa um sábio ancestral que segura um cajado em uma das mãos e uma pena sagrada na outra. Sua perna direita foi cortada pela esposa ciumenta. Quando Tuira surge a Leste no início da noite (dezembro/janeiro), ele traz a mensagem de que as primeiras chuvas fertilizarão a terra, sinalizando o momento ideal para o plantio tradicional do milho e da mandioca no litoral paraibano.",
                "/audio/tuira_tupi.mp3",
                "/audio/tuira_pt.mp3",
                142
        );

        tuira.addStar(new ConstellationStar("Cabeça de Tuira", "Betelgeuse (Alpha Orionis)", "Supergigante vermelha brilhante que ilumina o semblante do sábio", 0.5));
        tuira.addStar(new ConstellationStar("Braço Direito", "Bellatrix (Gamma Orionis)", "Braço erguido apontando para as chuvas nascentes", 1.64));
        tuira.addStar(new ConstellationStar("Cajado Sagrado", "Três Marias (Alnitak, Alnilam, Mintaka)", "O cinto de sustentação cósmica e bordão do ancião", 1.7));
        tuira.addStar(new ConstellationStar("Perna Inteira", "Saiph (Kappa Orionis)", "Perna apoiada no firmamento", 2.07));
        tuira.addStar(new ConstellationStar("Perna Cortada (Toco)", "Rigel (Beta Orionis)", "Ponto onde a perna foi cortada no mito sagrado", 0.18));
        tuira.addStar(new ConstellationStar("Cesto de Sementes / Urna", "Plêiades (Seichu)", "Cesto celeste com as sementes que fecundarão os roçados", 1.6));
        tuira.addStar(new ConstellationStar("Mandíbula do Velho", "Híades & Aldebaran", "Queixo do ancião voltado para o caminho do sol", 0.85));

        constellationRepository.save(tuira);

        // 2. A Ema Celeste (Guyra Nhandu)
        Constellation ema = new Constellation(
                "ema_celeste",
                "A EMA CELESTE",
                "Guyra Nhandu",
                "Potiguara / Tupi-Guarani",
                "O Mito & a Observação no RN e PB",
                "CONCLUIDO",
                "MESTRE 🏆",
                100,
                "/assets/ema_celeste.jpg",
                "Cruzeiro do Sul e Mosca",
                "Solstício de inverno e cheia das marés",
                "Junho a Agosto",
                "Litoral Potiguar",
                Arrays.asList("Áudio do Pajé 🎧", "Lição Sagrada 📖", "Stellarium 🔭", "Exercício ✍️"),
                "Guyra Nhandu ybype oĩ...",
                "A Ema Celeste é uma das maiores constelações da etnoastronomia brasileira. Formada por nebulosas escuras e estrelas da Via Láctea, a cabeça da Ema é o Saco de Carvão e suas penas espalham-se até o Cruzeiro do Sul. Quando surge inteira na direção Sul no meio do ano, anuncia a chegada do frio relativo do inverno, ventos fortes e a elevação das marés para a pesca artesanal.",
                "/audio/ema_tupi.mp3",
                "/audio/ema_pt.mp3",
                180
        );
        constellationRepository.save(ema);

        // 3. Caminho das Antas (Tapi'i Rape)
        Constellation antas = new Constellation(
                "caminho_antas",
                "CAMINHO DAS ANTAS",
                "Tapi'i Rape",
                "Potiguara / Tupi-Guarani",
                "A Via Láctea como trilha espiritual",
                "BLOQUEADO",
                "BLOQUEADO 🔒",
                0,
                "/assets/antas_milkyway.jpg",
                "Faixa Central da Via Láctea",
                "Estrada dos antepassados e orientação noturna",
                "Maio a Outubro",
                "Nordeste Brasileiro",
                Arrays.asList("Áudio Ancestral 🎧", "Lição Sagrada 📖", "Stellarium 🔭"),
                "Tapi'i rape omohesakã yby...",
                "A Via Láctea é vista pelos povos Tupi e Potiguara como a grande trilha por onde transitam as almas dos ancestrais e os animais sagrados. Ela cruza o céu de ponta a ponta e serve de guia infalível para navegadores costeiros e caçadores na mata.",
                "/audio/antas_tupi.mp3",
                "/audio/antas_pt.mp3",
                120
        );
        constellationRepository.save(antas);

        // 4. O Veado Sagrado (Guata Guasu)
        Constellation veado = new Constellation(
                "veado",
                "O VEADO SAGRADO",
                "Guata Guasu",
                "Potiguara / Tupi-Guarani",
                "Renovação e Germinação das Matas",
                "BLOQUEADO",
                "BLOQUEADO 🔒",
                0,
                "/assets/veado_celeste.jpg",
                "Escorpião e Sagitário",
                "Primavera e florescimento da mata litorânea",
                "Setembro a Novembro",
                "Baía da Traição - PB",
                Arrays.asList("Áudio do Ancião 🎧", "Lição 📖"),
                "Guata Guasu oikove...",
                "Constelação que desponta na primavera anunciando a brotação de novas folhas e o período reprodutivo dos animais da Mata Atlântica de restinga.",
                "/audio/veado_tupi.mp3",
                "/audio/veado_pt.mp3",
                110
        );
        constellationRepository.save(veado);
    }

    private void initObservationGuides() {
        ObservationGuide guideHomemVelho = new ObservationGuide(
                "homem_velho",
                "O Homem Velho (Tuira)",
                78.5,
                42.0,
                "21:30 (Horário de Brasília)",
                "Leste (E) - Nascente",
                "Baía da Traição - PB (Lat: -6.68°, Long: -34.93°)",
                -6.68,
                -34.93,
                "60°",
                "15 de Janeiro",
                "Para melhor visualização no Stellarium ou a olho nu, posicione-se em praia aberta sem poluição luminosa artificial, olhando para a direção onde o sol nasce."
        );

        guideHomemVelho.addStep(new ObservationStep(
                1,
                "Configurar Data e Horário no Stellarium",
                "Abra o Stellarium e ajuste a data para 15 de Janeiro às 21:30. Selecione a localização como Baía da Traição ou João Pessoa - PB.",
                "SET_DATETIME_2026_01_15_2130",
                "Tecla F5 no Stellarium para abrir a janela de data/hora."
        ));

        guideHomemVelho.addStep(new ObservationStep(
                2,
                "Orientação para o Horizonte Leste",
                "Gire a visualização em direção ao ponto cardeal Leste (Azimute ~78°). A constelação estará a aproximadamente 40° acima do horizonte.",
                "LOOK_EAST_AZ78_ALT42",
                "Use as setas do teclado ou arraste o céu com o mouse."
        ));

        guideHomemVelho.addStep(new ObservationStep(
                3,
                "Localizar Estrelas Guia: Três Marias e Aldebaran",
                "Encontre as Três Marias (o cajado de Tuira). Logo acima à esquerda estará Betelgeuse (sua cabeça) e mais ao norte o aglomerado das Híades com Aldebaran (sua mandíbula).",
                "HIGHLIGHT_GUIDE_STARS",
                "Pressione a tecla 'C' para ligar as linhas das constelações ocidentais se desejar comparar."
        ));

        guideHomemVelho.addStep(new ObservationStep(
                4,
                "Reconhecer a Silhueta Ancestral de Tuira",
                "Agora visualize o corpo completo: o sábio curvado, a perna cortada terminando em Rigel, e o cesto sagrado das Plêiades (Seichu) à frente.",
                "OVERLAY_TUIRA_ART",
                "Ative a arte etnoastronômica para enxergar o Homem Velho como os Potiguaras contemplam há séculos."
        ));

        guideRepository.save(guideHomemVelho);

        // Guia para Ema Celeste
        ObservationGuide guideEma = new ObservationGuide(
                "ema_celeste",
                "A Ema Celeste (Guyra Nhandu)",
                172.0,
                55.0,
                "22:00 (Horário de Brasília)",
                "Sul (S) - Cruzeiro do Sul",
                "Baía da Traição - PB (Lat: -6.68°, Long: -34.93°)",
                -6.68,
                -34.93,
                "75°",
                "21 de Junho",
                "A Ema é melhor visualizada no solstício de inverno, quando as noites são mais límpidas e escuras no Nordeste."
        );

        guideEma.addStep(new ObservationStep(
                1,
                "Ajustar Horário de Inverno",
                "Defina a data no Stellarium para 21 de Junho às 22:00, olhando em direção ao Sul.",
                "SET_DATETIME_WINTER",
                "Horário propício com a Via Láctea no zênite."
        ));

        guideEma.addStep(new ObservationStep(
                2,
                "Identificar o Saco de Carvão",
                "Procure a mancha escura na Via Láctea ao lado da estrela Mimosa do Cruzeiro do Sul. Esta mancha é a cabeça da Ema.",
                "LOOK_COAL_SACK",
                "Nebulosa escura de poeira interestelar."
        ));

        guideRepository.save(guideEma);
    }

    private void initExercises() {
        Exercise exercise = new Exercise(
                "exercicio_associacao_ecologica",
                "Associação Cosmológica & Ecológica",
                "Conecte os Saberes do Céu aos Ciclos da Terra",
                "Clique em uma Constelação Ancestral na coluna da esquerda e, em seguida, selecione o Fenômeno Ecológico correspondente na coluna da direita.",
                100,
                4
        );

        exercise.addPair(new ExercisePair(
                "homem_velho",
                "O Homem Velho (Tuira)",
                "Orion e Touro • Leste",
                "/assets/tuira_avatar.jpg",
                "chuvas_milho",
                "Início das chuvas e época de plantar o milho",
                "Ciclo Agrícola • Verão do Nordeste",
                "/assets/rain_season.jpg",
                "O aparecimento do Homem Velho a Leste em dezembro/janeiro marca o início do período chuvoso e orienta os agricultores Potiguaras no preparo e semeadura da terra."
        ));

        exercise.addPair(new ExercisePair(
                "ema_celeste",
                "Guyra Nhandu (Ema Celeste)",
                "Cruzeiro do Sul e Mosca • Sul",
                "/assets/ema_celeste.jpg",
                "solsticio_mares",
                "Solstício de inverno e cheia das marés",
                "Ciclo Marítimo e Pesca • Junho",
                "/assets/fishing_tides.jpg",
                "Quando a Ema surge por completo no céu do Sul, coincide com o solstício de inverno, tempo de ventos fortes, marés altas e movimentação dos cardumes litorâneos."
        ));

        exercise.addPair(new ExercisePair(
                "caminho_antas",
                "Caminho das Antas (Via Láctea)",
                "Tapi'i Rape • Céu Profundo",
                "/assets/antas_milkyway.jpg",
                "estrada_antepassados",
                "Estrada dos antepassados e orientação noturna",
                "Espiritualidade & Navegação Costeira",
                "/assets/starfield_bg.jpg",
                "O rio estelar da Via Láctea é a senda sagrada por onde viajam os espíritos dos antepassados e serve como bússola natural para a travessia noturna na mata e no mar."
        ));

        exercise.addPair(new ExercisePair(
                "veado",
                "O Veado Sagrado (Guata Guasu)",
                "Escorpião e Sagitário",
                "/assets/veado_celeste.jpg",
                "primavera_renovacao",
                "Primavera e florescimento da mata litorânea",
                "Regeneração da Floresta e Fauna • Setembro",
                "/assets/corn_harvest.jpg",
                "A aparição desta constelação aponta para o despertar da fauna nativa e o florescimento das árvores frutíferas da Mata Atlântica de restinga."
        ));

        exerciseRepository.save(exercise);
    }

    private void initUserProfile() {
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        UserProfile user = new UserProfile(
                "yara",
                "yara@potiguara.org",
                encoder.encode("123456"),
                "Yara R.",
                "/assets/tuira_avatar.jpg",
                "Exploradora Cósmica • Nível 2",
                650,
                1000,
                2,
                2,
                "Lua Crescente (Jaci) • Estação das Chuvas"
        );

        user.addProgress(new UserProgress("homem_velho", "EM_PROGRESSO", 85, true, true, true, false));
        user.addProgress(new UserProgress("ema_celeste", "CONCLUIDO", 100, true, true, true, true));
        user.addProgress(new UserProgress("caminho_antas", "BLOQUEADO", 0, false, false, false, false));
        user.addProgress(new UserProgress("veado", "BLOQUEADO", 0, false, false, false, false));

        userProfileRepository.save(user);
    }
}
