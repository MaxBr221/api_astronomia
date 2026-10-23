-- ==============================================================================
-- V2: Carga Inicial de Dados Ancestrais da Astronomia Potiguara
-- ==============================================================================

-- 1. Inserir Constelações
INSERT INTO constellations (
    id, code, title, indigenous_name, culture, subtitle, status, badge_label,
    progress, image_url, western_equivalence, seasonal_meaning, observation_season,
    region, narrative_tupi, narrative_portuguese, audio_url_tupi, audio_url_portuguese, audio_duration_seconds
) VALUES 
(
    1, 'homem_velho', 'HOMEM VELHO', 'Tuira', 'Potiguara / Tupi-Guarani',
    'A Criação & Ciclos da Natureza', 'LIBERADO', 'LIBERADO', 85,
    '/assets/tuira_orion.jpg', 'Orion e Touro', 'Início das chuvas e época de plantar o milho',
    'Dezembro a Março', 'Baía da Traição, Litoral Norte da Paraíba',
    'Tuira tuwixaba ybyrapytá rupí, amana oky oje''ea...',
    'Segundo os sábios e pajés da tradição Potiguara e Tupi-Guarani, o Homem Velho (Tuira) representa um sábio ancestral que segura um cajado em uma das mãos e uma pena sagrada na outra. Sua perna direita foi cortada pela esposa ciumenta. Quando Tuira surge a Leste no início da noite (dezembro/janeiro), ele traz a mensagem de que as primeiras chuvas fertilizarão a terra, sinalizando o momento ideal para o plantio tradicional do milho e da mandioca no litoral paraibano.',
    '/audio/tuira_tupi.mp3', '/audio/tuira_pt.mp3', 142
),
(
    2, 'ema_celeste', 'A EMA CELESTE', 'Guyra Nhandu', 'Potiguara / Tupi-Guarani',
    'O Mito & a Observação no RN e PB', 'CONCLUIDO', 'MESTRE 🏆', 100,
    '/assets/ema_celeste.jpg', 'Cruzeiro do Sul e Mosca', 'Solstício de inverno e cheia das marés',
    'Junho a Agosto', 'Litoral Potiguar',
    'Guyra Nhandu ybype oĩ...',
    'A Ema Celeste é uma das maiores constelações da etnoastronomia brasileira. Formada por nebulosas escuras e estrelas da Via Láctea, a cabeça da Ema é o Saco de Carvão e suas penas espalham-se até o Cruzeiro do Sul. Quando surge inteira na direção Sul no meio do ano, anuncia a chegada do frio relativo do inverno, ventos fortes e a elevação das marés para a pesca artesanal.',
    '/audio/ema_tupi.mp3', '/audio/ema_pt.mp3', 180
),
(
    3, 'caminho_antas', 'CAMINHO DAS ANTAS', 'Tapi''i Rape', 'Potiguara / Tupi-Guarani',
    'A Via Láctea como trilha espiritual', 'BLOQUEADO', 'BLOQUEADO 🔒', 0,
    '/assets/antas_milkyway.jpg', 'Faixa Central da Via Láctea', 'Estrada dos antepassados e orientação noturna',
    'Maio a Outubro', 'Nordeste Brasileiro',
    'Tapi''i rape omohesakã yby...',
    'A Via Láctea é vista pelos povos Tupi e Potiguara como a grande trilha por onde transitam as almas dos ancestrais e os animais sagrados. Ela cruza o céu de ponta a ponta e serve de guia infalível para navegadores costeiros e caçadores na mata.',
    '/audio/antas_tupi.mp3', '/audio/antas_pt.mp3', 120
),
(
    4, 'veado', 'O VEADO SAGRADO', 'Guata Guasu', 'Potiguara / Tupi-Guarani',
    'Renovação e Germinação das Matas', 'BLOQUEADO', 'BLOQUEADO 🔒', 0,
    '/assets/starfield_bg.jpg', 'Escorpião e Sagitário', 'Primavera e florescimento da mata litorânea',
    'Setembro a Novembro', 'Baía da Traição - PB',
    'Guata Guasu oikove...',
    'Constelação que desponta na primavera anunciando a brotação de novas folhas e o período reprodutivo dos animais da Mata Atlântica de restinga.',
    '/audio/veado_tupi.mp3', '/audio/veado_pt.mp3', 110
)
ON CONFLICT (id) DO NOTHING;

-- Atualizar sequence de constelações
SELECT setval('constellations_id_seq', (SELECT MAX(id) FROM constellations));

-- 2. Inserir Badges / Atividades das Constelações
INSERT INTO constellation_badges (constellation_id, badge) VALUES
(1, 'Áudio do Ancião 🎧'),
(1, 'Lição Sagrada 📖'),
(1, 'Stellarium 🔭'),
(1, 'Exercício ✍️'),
(2, 'Áudio do Pajé 🎧'),
(2, 'Lição Sagrada 📖'),
(2, 'Stellarium 🔭'),
(2, 'Exercício ✍️'),
(3, 'Áudio Ancestral 🎧'),
(3, 'Lição Sagrada 📖'),
(3, 'Stellarium 🔭'),
(4, 'Áudio do Ancião 🎧'),
(4, 'Lição 📖');

-- 3. Inserir Estrelas de Tuira (Homem Velho)
INSERT INTO constellation_stars (constellation_id, indigenous_part, western_star, astronomical_meaning, apparent_magnitude) VALUES
(1, 'Cabeça de Tuira', 'Betelgeuse (Alpha Orionis)', 'Supergigante vermelha brilhante que ilumina o semblante do sábio', 0.5),
(1, 'Braço Direito', 'Bellatrix (Gamma Orionis)', 'Braço erguido apontando para as chuvas nascentes', 1.64),
(1, 'Cajado Sagrado', 'Três Marias (Alnitak, Alnilam, Mintaka)', 'O cinto de sustentação cósmica e bordão do ancião', 1.7),
(1, 'Perna Inteira', 'Saiph (Kappa Orionis)', 'Perna apoiada no firmamento', 2.07),
(1, 'Perna Cortada (Toco)', 'Rigel (Beta Orionis)', 'Ponto onde a perna foi cortada no mito sagrado', 0.18),
(1, 'Cesto de Sementes / Urna', 'Plêiades (Seichu)', 'Cesto celeste com as sementes que fecundarão os roçados', 1.6),
(1, 'Mandíbula do Velho', 'Híades & Aldebaran', 'Queixo do ancião voltado para o caminho do sol', 0.85);

-- 4. Inserir Guias de Observação / Stellarium
INSERT INTO observation_guides (
    id, constellation_code, constellation_title, azimuth, altitude, best_time,
    cardinal_direction, location_name, latitude, longitude, fov, recommended_date, observational_notes
) VALUES 
(
    1, 'homem_velho', 'O Homem Velho (Tuira)', 78.5, 42.0, '21:30 (Horário de Brasília)',
    'Leste (E) - Nascente', 'Baía da Traição - PB (Lat: -6.68°, Long: -34.93°)',
    -6.68, -34.93, '60°', '15 de Janeiro',
    'Para melhor visualização no Stellarium ou a olho nu, posicione-se em praia aberta sem poluição luminosa artificial, olhando para a direção onde o sol nasce.'
),
(
    2, 'ema_celeste', 'A Ema Celeste (Guyra Nhandu)', 172.0, 55.0, '22:00 (Horário de Brasília)',
    'Sul (S) - Cruzeiro do Sul', 'Baía da Traição - PB (Lat: -6.68°, Long: -34.93°)',
    -6.68, -34.93, '75°', '21 de Junho',
    'A Ema é melhor visualizada no solstício de inverno, quando as noites são mais límpidas e escuras no Nordeste.'
)
ON CONFLICT (id) DO NOTHING;

SELECT setval('observation_guides_id_seq', (SELECT MAX(id) FROM observation_guides));

-- 5. Inserir Passos de Observação
INSERT INTO observation_steps (guide_id, step_number, title, description, stellarium_action, tool_tip) VALUES
(1, 1, 'Configurar Data e Horário no Stellarium', 'Abra o Stellarium e ajuste a data para 15 de Janeiro às 21:30. Selecione a localização como Baía da Traição ou João Pessoa - PB.', 'SET_DATETIME_2026_01_15_2130', 'Tecla F5 no Stellarium para abrir a janela de data/hora.'),
(1, 2, 'Orientação para o Horizonte Leste', 'Gire a visualização em direção ao ponto cardeal Leste (Azimute ~78°). A constelação estará a aproximadamente 40° acima do horizonte.', 'LOOK_EAST_AZ78_ALT42', 'Use as setas do teclado ou arraste o céu com o mouse.'),
(1, 3, 'Localizar Estrelas Guia: Três Marias e Aldebaran', 'Encontre as Três Marias (o cajado de Tuira). Logo acima à esquerda estará Betelgeuse (sua cabeça) e mais ao norte o aglomerado das Híades com Aldebaran (sua mandíbula).', 'HIGHLIGHT_GUIDE_STARS', 'Pressione a tecla ''C'' para ligar as linhas das constelações ocidentais se desejar comparar.'),
(1, 4, 'Reconhecer a Silhueta Ancestral de Tuira', 'Agora visualize o corpo completo: o sábio curvado, a perna cortada terminando em Rigel, e o cesto sagrado das Plêiades (Seichu) à frente.', 'OVERLAY_TUIRA_ART', 'Ative a arte etnoastronômica para enxergar o Homem Velho como os Potiguaras contemplam há séculos.'),
(2, 1, 'Ajustar Horário de Inverno', 'Defina a data no Stellarium para 21 de Junho às 22:00, olhando em direção ao Sul.', 'SET_DATETIME_WINTER', 'Horário propício com a Via Láctea no zênite.'),
(2, 2, 'Identificar o Saco de Carvão', 'Procure a mancha escura na Via Láctea ao lado da estrela Mimosa do Cruzeiro do Sul. Esta mancha é a cabeça da Ema.', 'LOOK_COAL_SACK', 'Nebulosa escura de poeira interestelar.');

-- 6. Inserir Exercícios Interativos
INSERT INTO exercises (id, code, title, subtitle, instructions, reward_xp, total_pairs) VALUES
(
    1, 'exercicio_associacao_ecologica', 'Associação Cosmológica & Ecológica',
    'Conecte os Saberes do Céu aos Ciclos da Terra',
    'Clique em uma Constelação Ancestral na coluna da esquerda e, em seguida, selecione o Fenômeno Ecológico correspondente na coluna da direita.',
    100, 4
)
ON CONFLICT (id) DO NOTHING;

SELECT setval('exercises_id_seq', (SELECT MAX(id) FROM exercises));

-- 7. Inserir Pares de Associação
INSERT INTO exercise_pairs (
    exercise_id, constellation_key, constellation_label, constellation_subtitle, constellation_icon,
    phenomenon_key, phenomenon_label, phenomenon_subtitle, phenomenon_icon, explanation
) VALUES
(
    1, 'homem_velho', 'O Homem Velho (Tuira)', 'Orion e Touro • Leste', '/assets/tuira_avatar.jpg',
    'chuvas_milho', 'Início das chuvas e época de plantar o milho', 'Ciclo Agrícola • Verão do Nordeste', '/assets/rain_season.jpg',
    'O aparecimento do Homem Velho a Leste em dezembro/janeiro marca o início do período chuvoso e orienta os agricultores Potiguaras no preparo e semeadura da terra.'
),
(
    1, 'ema_celeste', 'Guyra Nhandu (Ema Celeste)', 'Cruzeiro do Sul e Mosca • Sul', '/assets/ema_celeste.jpg',
    'solsticio_mares', 'Solstício de inverno e cheia das marés', 'Ciclo Marítimo e Pesca • Junho', '/assets/fishing_tides.jpg',
    'Quando a Ema surge por completo no céu do Sul, coincide com o solstício de inverno, tempo de ventos fortes, marés altas e movimentação dos cardumes litorâneos.'
),
(
    1, 'caminho_antas', 'Caminho das Antas (Via Láctea)', 'Tapi''i Rape • Céu Profundo', '/assets/antas_milkyway.jpg',
    'estrada_antepassados', 'Estrada dos antepassados e orientação noturna', 'Espiritualidade & Navegação Costeira', '/assets/starfield_bg.jpg',
    'O rio estelar da Via Láctea é a senda sagrada por onde viajam os espíritos dos antepassados e serve como bússola natural para a travessia noturna na mata e no mar.'
),
(
    1, 'veado', 'O Veado Sagrado (Guata Guasu)', 'Escorpião e Sagitário', '/assets/starfield_bg.jpg',
    'primavera_renovacao', 'Primavera e florescimento da mata litorânea', 'Regeneração da Floresta e Fauna • Setembro', '/assets/corn_harvest.jpg',
    'A aparição desta constelação aponta para o despertar da fauna nativa e o florescimento das árvores frutíferas da Mata Atlântica de restinga.'
);

-- 8. Inserir Perfil de Usuário Inicial (Yara)
-- Senha: BCrypt hash de '123456'
INSERT INTO user_profiles (
    id, username, email, password, full_name, avatar_url, level, xp, max_xp, lives, max_lives, lunar_cycle
) VALUES 
(
    1, 'yara', 'yara@potiguara.org',
    '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.',
    'Yara R.', '/assets/tuira_avatar.jpg', 'Exploradora Cósmica • Nível 2',
    650, 1000, 2, 2, 'Lua Crescente (Jaci) • Estação das Chuvas'
)
ON CONFLICT (id) DO NOTHING;

SELECT setval('user_profiles_id_seq', (SELECT MAX(id) FROM user_profiles));

-- 9. Inserir Progresso Inicial da Usuária Yara
INSERT INTO user_node_progress (
    user_id, constellation_code, status, progress, audio_completed, lesson_completed, stellarium_completed, exercise_completed
) VALUES
(1, 'homem_velho', 'EM_PROGRESSO', 85, TRUE, TRUE, TRUE, FALSE),
(1, 'ema_celeste', 'CONCLUIDO', 100, TRUE, TRUE, TRUE, TRUE),
(1, 'caminho_antas', 'BLOQUEADO', 0, FALSE, FALSE, FALSE, FALSE),
(1, 'veado', 'BLOQUEADO', 0, FALSE, FALSE, FALSE, FALSE);
