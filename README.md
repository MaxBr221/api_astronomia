# 🌌 API Astronomia Indígena Potiguara & Guia Stellarium

API RESTful desenvolvida em **Java 21**, **Spring Boot 3** e **PostgreSQL 17** para o sistema de difusão cosmológica e etnoastronomia ancestral dos povos Potiguaras e Tupi-Guarani, com integração ao guia de observação no software astronômico Stellarium e exercícios interativos com gamificação.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21 (LTS)
- **Framework:** Spring Boot 3.3.5
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-validation`
- **Banco de Dados:** PostgreSQL 17 (Database: `astronomia_db`)
- **Documentação de API:** OpenAPI 3 & Swagger UI (`springdoc-openapi`)
- **Gerenciador de Dependências & Build:** Apache Maven 3.9

---

## 🏛️ Arquitetura do Sistema

```
c:/api-astronomia/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/astronomia/potiguara/
│   │   │   ├── ApiAstronomiaApplication.java
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java           # Habilita CORS completo para React/TypeScript (portas 5173, 3000, etc.)
│   │   │   │   ├── DataInitializer.java     # Carga inicial com saberes etnoastronômicos Potiguara
│   │   │   │   └── OpenApiConfig.java       # Configurações do Swagger 3 UI
│   │   │   ├── controller/
│   │   │   │   ├── ConstellationController.java
│   │   │   │   ├── ObservationGuideController.java
│   │   │   │   ├── ExerciseController.java
│   │   │   │   └── UserController.java
│   │   │   ├── dto/
│   │   │   │   ├── PairValidationResult.java
│   │   │   │   ├── UpdateProgressRequest.java
│   │   │   │   ├── ValidateExerciseRequest.java
│   │   │   │   └── ValidateExerciseResponse.java
│   │   │   ├── model/
│   │   │   │   ├── Constellation.java
│   │   │   │   ├── ConstellationStar.java
│   │   │   │   ├── Exercise.java
│   │   │   │   ├── ExercisePair.java
│   │   │   │   ├── ObservationGuide.java
│   │   │   │   ├── ObservationStep.java
│   │   │   │   ├── UserProfile.java
│   │   │   │   └── UserProgress.java
│   │   │   ├── repository/
│   │   │   │   ├── ConstellationRepository.java
│   │   │   │   ├── ExerciseRepository.java
│   │   │   │   ├── ObservationGuideRepository.java
│   │   │   │   ├── UserProfileRepository.java
│   │   │   │   └── UserProgressRepository.java
│   │   │   └── service/
│   │   │       ├── ConstellationService.java
│   │   │       ├── ExerciseService.java
│   │   │       ├── ObservationGuideService.java
│   │   │       └── UserService.java
│   │   └── resources/
│   │       └── application.yml
```

---

## 🚀 Como Executar o Back End

### 1. Pré-requisitos
- **Java 21** instalado (`java -version`)
- **Maven** instalado (`mvn -v`)
- **PostgreSQL 17** em execução na porta `5432` com banco `astronomia_db`

### 2. Configuração do Banco no `application.yml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/astronomia_db
    username: postgres
    password: ${DB_PASSWORD:12345678}
```

### 3. Compilar e Gerar o Executável
```powershell
mvn clean package -DskipTests
```

### 4. Executar a Aplicação
```powershell
java -jar target/api-astronomia-1.0.0.jar
# ou diretamente via Maven:
mvn spring-boot:run
```

A aplicação subirá em: `http://localhost:8080/`

---

## 📖 Documentação Interativa (Swagger UI)

Acesse no navegador com o backend ativo:
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📡 Endpoints REST Disponíveis

### 1. Autenticação & Cadastro (Novo 🔐)
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/auth/cadastro` | Registra um novo explorador cósmico (`username`, `email`, `password`, `fullName`, `avatarUrl`) e inicializa a trilha |
| `POST` | `/api/auth/login` | Autentica via login (`identifier` = e-mail ou username, e `password`), retornando token e perfil |
| `GET` | `/api/auth/me` | Retorna o perfil do usuário ativo da sessão (ou query param `userId`) |

#### Exemplo de Cadastro (`POST /api/auth/cadastro`):
```json
{
  "username": "tupi_explorador",
  "email": "explorador@potiguara.org",
  "password": "senhaSegura123",
  "fullName": "Kauã Potiguara",
  "avatarUrl": "/assets/tuira_avatar.jpg"
}
```

#### Exemplo de Login (`POST /api/auth/login`):
```json
{
  "identifier": "yara@potiguara.org",
  "password": "123456"
}
```

### 2. Trilha Celeste & Constelações
| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/constelacoes` | Lista todas as constelações da trilha (Homem Velho, Ema Celeste, Caminho das Antas, Veado) com status, progresso e equivalências ocidentais |
| `GET` | `/api/constelacoes/{code}` | Detalhes completos da constelação (ex: `homem_velho`), incluindo narrativa em Tupi e Português, áudios e estrelas correspondentes |

### 2. Guia de Observação & Stellarium
| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/observatorio` | Lista os guias de observação astronômica cadastrados |
| `GET` | `/api/observatorio/{constellationCode}` | Retorna as coordenadas celestes (Azimute, Altitude, Data/Horário, Localização: Baía da Traição - PB) e passos de busca no Stellarium |

### 3. Exercícios Interativos (Associação Ecológica)
| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/exercicios` | Lista todos os exercícios interativos |
| `GET` | `/api/exercicios/{code}` | Retorna dados e pares do exercício (ex: `exercicio_associacao_ecologica`) |
| `POST` | `/api/exercicios/{code}/validar` | Valida os pares conectados pelo aluno, calcula pontuação, concede XP e atualiza o progresso |

#### Exemplo de Payload para Validação (`POST /api/exercicios/exercicio_associacao_ecologica/validar`):
```json
{
  "userId": 1,
  "answers": {
    "homem_velho": "chuvas_milho",
    "ema_celeste": "solsticio_mares",
    "caminho_antas": "estrada_antepassados",
    "veado": "primavera_renovacao"
  }
}
```

### 4. Perfil do Explorador & Gamificação
| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/usuarios/perfil` | Retorna o perfil ativo do estudante/explorador (Nível, XP, Vidas, Ciclo Lunar) |
| `GET` | `/api/usuarios/{id}` | Perfil por ID |
| `POST` | `/api/usuarios/{id}/progresso` | Atualiza o progresso individual dos nós da trilha (áudio, lição, stellarium, exercício) |
| `POST` | `/api/usuarios/{id}/recuperar-vidas` | Restaura as vidas do explorador |

---

## 🤝 Integração com o Front End (React + TypeScript)
- A configuração de **CORS** (`CorsConfig.java`) já está pré-configurada para aceitar requisições de origens locais típicas do front-end (`http://localhost:5173`, `http://localhost:3000`, etc.) sem restrições.
- As respostas JSON utilizam padrões camelCase compatíveis com interfaces TypeScript.
