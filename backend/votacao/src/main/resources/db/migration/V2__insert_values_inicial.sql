INSERT INTO assembleias (id, nome, data_criacao)
VALUES
(1, 'Assembleia Geral Ordinária', '2025-01-04 09:00:00'),
(2, 'Assembleia Extraordinária', '2025-01-04 10:00:00');

INSERT INTO pautas (id, titulo, descricao, data_criacao, assembleia_id)
VALUES
(1, 'Aprovação do Orçamento', 'Discussão e aprovação do orçamento para 2025.', '2025-01-04 09:30:00', 1),
(2, 'Revisão de Estatuto', 'Alterações no estatuto da empresa.', '2025-01-04 10:30:00', 2);

INSERT INTO associados (id, nome, cpf, status)
VALUES
(1, 'João da Silva', '12345678901', 'ABLE_TO_VOTE'),
(2, 'Maria Oliveira', '98765432100', 'ABLE_TO_VOTE');

INSERT INTO sessoes (id, inicio, fim, pauta_id)
VALUES
(1, '2025-01-04 09:00:00', '2025-01-04 10:00:00', 1),
(2, '2025-01-04 10:30:00', '2025-01-04 11:30:00', 2);

INSERT INTO votos (id, voto, sessao_id, associado_id, pauta_id)
VALUES
(1, 'SIM', 1, 1, 1),
(2, 'NAO', 2, 2, 1);
