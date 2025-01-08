package com.db.votacao.model.dto.response;

public record ResultadoVotacaoResponseDTO(
        Long pautaId,
        String pautaTitulo,
        int totalVotos,
        int votosSim,
        int votosNao,
        String resultado
) {
}
