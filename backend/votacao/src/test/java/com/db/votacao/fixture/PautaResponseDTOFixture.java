package com.db.votacao.fixture;

import com.db.votacao.model.dto.response.PautaResponseDTO;

public class PautaResponseDTOFixture {

    public static PautaResponseDTO criarPautaResponseDTOPadrao() {
        return new PautaResponseDTO(
                1L,
                "Pauta Teste",
                "descrição teste",
                null
        );
    }
}
