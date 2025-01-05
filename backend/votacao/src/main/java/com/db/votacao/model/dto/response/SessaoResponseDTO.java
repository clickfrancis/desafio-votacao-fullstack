package com.db.votacao.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record SessaoResponseDTO(
        Long id,
        LocalDateTime inicio,
        LocalDateTime fim,
        PautaResponseDTO pauta,
        List<VotoResponseDTO> votos
) {
}
