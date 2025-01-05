CREATE TABLE assembleias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE pautas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    assembleia_id BIGINT NOT NULL,
    FOREIGN KEY (assembleia_id) REFERENCES assembleias(id)
);

CREATE TABLE associados (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE sessoes (
    id SERIAL PRIMARY KEY,
    inicio TIMESTAMP NOT NULL,
    fim TIMESTAMP NOT NULL,
    pauta_id BIGINT NOT NULL,
    FOREIGN KEY (pauta_id) REFERENCES pautas(id)
);

CREATE TABLE votos (
    id SERIAL PRIMARY KEY,
    voto VARCHAR(10) NOT NULL,
    sessao_id BIGINT NOT NULL,
    pauta_id BIGINT NOT NULL,  -- Adicionando a coluna pauta_id
    associado_id BIGINT NOT NULL,
    FOREIGN KEY (sessao_id) REFERENCES sessoes(id),
    FOREIGN KEY (pauta_id) REFERENCES pautas(id), -- Adicionando a chave estrangeira para pauta
    FOREIGN KEY (associado_id) REFERENCES associados(id),
    UNIQUE (sessao_id, associado_id)
);
