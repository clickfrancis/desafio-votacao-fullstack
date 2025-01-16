package com.db.votacao.model.dto.request;

public record SessaoRequestDTO(
        Long pautaId,
        Integer tempoEmMinutos
) {
}
