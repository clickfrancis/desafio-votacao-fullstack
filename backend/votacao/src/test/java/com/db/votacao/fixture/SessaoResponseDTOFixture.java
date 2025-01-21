package com.db.votacao.fixture;

import com.db.votacao.model.dto.response.PautaResponseDTO;
import com.db.votacao.model.dto.response.SessaoResponseDTO;
import com.db.votacao.model.dto.response.VotoResponseDTO;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class SessaoResponseDTOFixture {

    public static SessaoResponseDTO criarSessaoResponseDTO(
            Long id,
            LocalDateTime inicio,
            LocalDateTime fim,
            PautaResponseDTO pauta,
            List<VotoResponseDTO> votos) {
        return new SessaoResponseDTO(id, inicio, fim, pauta, votos);
    }

    public static SessaoResponseDTO criarSessaoResponseDTOPadrao() {
        return new SessaoResponseDTO(
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                PautaResponseDTOFixture.criarPautaResponseDTOPadrao(),
                Collections.emptyList()
        );
    }
}
