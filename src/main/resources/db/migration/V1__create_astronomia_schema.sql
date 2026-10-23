-- ==============================================================================
-- V1: Criação do Esquema de Banco de Dados da Astronomia Indígena Potiguara
-- ==============================================================================

-- 1. Tabela de Perfis de Usuário / Exploradores
CREATE TABLE IF NOT EXISTS user_profiles (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    full_name VARCHAR(255),
    avatar_url VARCHAR(255),
    level VARCHAR(255),
    xp INTEGER DEFAULT 0,
    max_xp INTEGER DEFAULT 500,
    lives INTEGER DEFAULT 3,
    max_lives INTEGER DEFAULT 3,
    lunar_cycle VARCHAR(255)
);

-- 2. Tabela de Progresso dos Nós da Trilha por Usuário
CREATE TABLE IF NOT EXISTS user_node_progress (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    constellation_code VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    progress INTEGER DEFAULT 0,
    audio_completed BOOLEAN DEFAULT FALSE,
    lesson_completed BOOLEAN DEFAULT FALSE,
    stellarium_completed BOOLEAN DEFAULT FALSE,
    exercise_completed BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_user_node_progress_user FOREIGN KEY (user_id) REFERENCES user_profiles(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_user_node_progress_user_id ON user_node_progress(user_id);
CREATE INDEX IF NOT EXISTS idx_user_node_progress_constellation ON user_node_progress(constellation_code);

-- 3. Tabela de Constelações Ancestrais
CREATE TABLE IF NOT EXISTS constellations (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(64) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    indigenous_name VARCHAR(255) NOT NULL,
    culture VARCHAR(255),
    subtitle VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    badge_label VARCHAR(255),
    progress INTEGER DEFAULT 0,
    image_url VARCHAR(255),
    western_equivalence VARCHAR(255),
    seasonal_meaning VARCHAR(255),
    observation_season VARCHAR(255),
    region VARCHAR(255),
    narrative_tupi TEXT,
    narrative_portuguese TEXT,
    audio_url_tupi VARCHAR(255),
    audio_url_portuguese VARCHAR(255),
    audio_duration_seconds INTEGER
);

-- Tabela de Atividades / Badges da Constelação
CREATE TABLE IF NOT EXISTS constellation_badges (
    constellation_id BIGINT NOT NULL,
    badge VARCHAR(255) NOT NULL,
    CONSTRAINT fk_constellation_badges_constellation FOREIGN KEY (constellation_id) REFERENCES constellations(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_constellation_badges_constellation_id ON constellation_badges(constellation_id);

-- 4. Tabela de Estrelas que formam as Constelações
CREATE TABLE IF NOT EXISTS constellation_stars (
    id BIGSERIAL PRIMARY KEY,
    constellation_id BIGINT NOT NULL,
    indigenous_part VARCHAR(255) NOT NULL,
    western_star VARCHAR(255) NOT NULL,
    astronomical_meaning VARCHAR(255),
    apparent_magnitude DOUBLE PRECISION,
    CONSTRAINT fk_constellation_stars_constellation FOREIGN KEY (constellation_id) REFERENCES constellations(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_constellation_stars_constellation_id ON constellation_stars(constellation_id);

-- 5. Tabela de Guias de Observação / Stellarium
CREATE TABLE IF NOT EXISTS observation_guides (
    id BIGSERIAL PRIMARY KEY,
    constellation_code VARCHAR(64) NOT NULL UNIQUE,
    constellation_title VARCHAR(255) NOT NULL,
    azimuth DOUBLE PRECISION,
    altitude DOUBLE PRECISION,
    best_time VARCHAR(255),
    cardinal_direction VARCHAR(255),
    location_name VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    fov VARCHAR(255),
    recommended_date VARCHAR(255),
    observational_notes TEXT
);

-- 6. Tabela de Passos de Observação no Stellarium
CREATE TABLE IF NOT EXISTS observation_steps (
    id BIGSERIAL PRIMARY KEY,
    guide_id BIGINT NOT NULL,
    step_number INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    stellarium_action VARCHAR(255),
    tool_tip VARCHAR(255),
    CONSTRAINT fk_observation_steps_guide FOREIGN KEY (guide_id) REFERENCES observation_guides(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_observation_steps_guide_id ON observation_steps(guide_id);

-- 7. Tabela de Exercícios Interativos
CREATE TABLE IF NOT EXISTS exercises (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(64) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255),
    instructions TEXT,
    reward_xp INTEGER DEFAULT 100,
    total_pairs INTEGER DEFAULT 4
);

-- 8. Tabela de Pares para Associação Ecológica (Ligar Colunas)
CREATE TABLE IF NOT EXISTS exercise_pairs (
    id BIGSERIAL PRIMARY KEY,
    exercise_id BIGINT NOT NULL,
    constellation_key VARCHAR(255) NOT NULL,
    constellation_label VARCHAR(255) NOT NULL,
    constellation_subtitle VARCHAR(255),
    constellation_icon VARCHAR(255),
    phenomenon_key VARCHAR(255) NOT NULL,
    phenomenon_label VARCHAR(255) NOT NULL,
    phenomenon_subtitle VARCHAR(255),
    phenomenon_icon VARCHAR(255),
    explanation TEXT,
    CONSTRAINT fk_exercise_pairs_exercise FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_exercise_pairs_exercise_id ON exercise_pairs(exercise_id);
