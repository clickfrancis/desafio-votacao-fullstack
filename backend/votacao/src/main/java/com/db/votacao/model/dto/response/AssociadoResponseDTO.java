package com.db.votacao.model.dto.response;

import com.db.votacao.model.Enum.StatusAssociado;

public record AssociadoResponseDTO(
        Long id,
        String nome,
        String cpf,
        StatusAssociado status
) {
}
