package com.db.votacao.model.dto.response;

import com.db.votacao.model.Enum.StatusAssociado;
import com.db.votacao.model.Voto;

import java.util.List;

public record AssociadoResponseDTO(
        Long id,
        String nome,
        String cpf,
        StatusAssociado status,
        List<Voto> votos
) {
}
