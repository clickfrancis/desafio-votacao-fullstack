package com.db.votacao.model.dto.response;

import java.util.List;

public record PautaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        AssembleiaResponseDTO assembleia,
        List<SessaoResponseDTO> sessoesVotacao,
        List<VotoResponseDTO>votos

) {
}
