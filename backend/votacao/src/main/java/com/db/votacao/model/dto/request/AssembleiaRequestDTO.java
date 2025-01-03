package com.db.votacao.model.dto.request;

import java.time.LocalDateTime;

public record AssembleiaRequestDTO(
        String nome,
        LocalDateTime dataCriacao
) {
}
