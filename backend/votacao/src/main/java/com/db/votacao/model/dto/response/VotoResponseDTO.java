package com.db.votacao.model.dto.response;

import com.db.votacao.model.Enum.VotoOpcao;

public record VotoResponseDTO(
        Long id,
        VotoOpcao voto,
        Long sessaoId,
        Long pautaId,
        Long associadoId
) {
}
