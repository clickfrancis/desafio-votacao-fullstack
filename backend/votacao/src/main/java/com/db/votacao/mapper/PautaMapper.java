package com.db.votacao.mapper;

import com.db.votacao.model.Assembleia;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PautaMapper {

    public Pauta toEntity(PautaRequestDTO pautaRequestDTO, Assembleia assembleia) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaRequestDTO.titulo());
        pauta.setDescricao(pautaRequestDTO.descricao());
        pauta.setAssembleia(assembleia);
        return pauta;
    }
}
