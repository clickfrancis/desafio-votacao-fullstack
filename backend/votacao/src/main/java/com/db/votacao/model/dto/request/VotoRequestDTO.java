package com.db.votacao.model.dto.request;

import com.db.votacao.model.Enum.VotoOpcao;

public record VotoRequestDTO(
        VotoOpcao votoOpcao,
        Long sessaoId,
        Long pautaId,
        Long associadoId
) {
}
