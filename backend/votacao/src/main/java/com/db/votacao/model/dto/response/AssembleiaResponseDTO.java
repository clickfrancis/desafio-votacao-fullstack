package com.db.votacao.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record AssembleiaResponseDTO(
        Long id,
        String nome,
        LocalDateTime dataCriacao,
        List<PautaResponseDTO> pautas
) {
}
