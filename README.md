# Votacao Application

Este repositório contém uma aplicação web para gerenciamento de votações. A solução foi desenvolvida utilizando **Java com Spring Boot** para o back-end e **PostgreSQL** como banco de dados. A aplicação está conteinerizada utilizando **Docker** e gerencia a evolução do banco de dados com **Flyway**.

---

## Funcionalidades

- Cadastro de pautas.
- Abertura de sessão de votação.
- Registro de votos (Sim/Não) para uma pauta.
- Consulta de resultados das votações.
- Listagem de todas as sessões de votação.

---

## Tecnologias Utilizadas

- **Linguagem**: Java 17
- **Framework**: Spring Boot
- **Banco de Dados**: PostgreSQL 15
- **Gerenciamento de Migração**: Flyway
- **Conteinerização**: Docker

---

## Requisitos

Certifique-se de ter instalado em sua máquina:

- **Docker** e **Docker Compose** (versão 3.8 ou superior)
- **Java 17**
- **Gradle** (para compilar o projeto)

---

## Configuração e Execução

### 1. Clonar o Repositório
```bash
git clone o projeto
cd votacao-app
```

### 2. Construir e Executar com Docker Compose

No diretório raiz do projeto, execute:
```bash
docker-compose up --build
```

### 3. Verificar os Contêineres em Execução

Certifique-se de que os contêineres estão em execução:
```bash
docker ps
```

### 4. Acessar a Aplicação

A API estará disponível em:
```plaintext
http://localhost:8081
```

---

## Endpoints da API

### **Pautas**
- **POST** `/pautas`: Cadastrar uma nova pauta.
- **GET** `/pautas`: Listar todas as pautas.
- **GET** `/pautas/{id}`: Consultar uma pauta pelo ID.

### **Sessões de Votação**
- **POST** `/sessoes`: Abrir uma nova sessão de votação.
- **GET** `/sessoes`: Listar todas as sessões.
- **GET** `/sessoes/{id}`: Consultar uma sessão pelo ID.

### **Votos**
- **POST** `/votos`: Registrar um voto em uma sessão.
- **GET** `/sessoes/{id}/resultado`: Consultar o resultado da votação em uma sessão.

---

## Variáveis de Ambiente

As seguintes variáveis de ambiente estão configuradas no arquivo `docker-compose.yml`:

| Variável                  | Descrição                        |
|---------------------------|------------------------------------|
| `SPRING_DATASOURCE_URL`   | URL de conexão com o banco de dados |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco de dados          |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco de dados           |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Estratégia de inicialização do banco |
| `SPRING_FLYWAY_ENABLED`   | Habilitar Flyway                  |
| `SPRING_FLYWAY_LOCATIONS` | Caminho dos scripts Flyway        |

---

## Banco de Dados

O PostgreSQL é inicializado com as seguintes credenciais:

- **Usuário**: postgres
- **Senha**: postgres
- **Nome do banco**: votacao

O volume `postgres_data` é utilizado para persistência dos dados.

---

## Migrações do Banco de Dados

As migrações do banco de dados são gerenciadas pelo Flyway. Os scripts de migração devem ser colocados na pasta `src/main/resources/db/migration` com o formato:

Exemplo:
```plaintext
V1__criar_tabela_pauta.sql
```

---

## Testes

### Executar os Testes Unitários

Para executar os testes unitários, utilize:
```bash
./gradlew test
```

---

## Melhorias Futuras

- Implementar autenticação e autorização.
- Adicionar paginação aos endpoints de listagem.

