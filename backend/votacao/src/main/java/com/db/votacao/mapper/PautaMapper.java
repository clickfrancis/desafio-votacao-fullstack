package com.db.votacao.mapper;

import com.db.votacao.model.Pauta;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import com.db.votacao.model.dto.response.AssembleiaResponseDTO;
import com.db.votacao.model.dto.response.PautaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PautaMapper {

    final SessaoMapper sessaoMapper;
    final VotoMapper votoMapper;

    public PautaMapper(
            SessaoMapper sessaoMapper,
            VotoMapper votoMapper
    ) {
        this.sessaoMapper = sessaoMapper;
        this.votoMapper = votoMapper;
    }

    public Pauta toEntity(PautaRequestDTO pautaRequestDTO) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaRequestDTO.titulo());
        pauta.setDescricao(pautaRequestDTO.descricao());
        return pauta;
    }

    public PautaResponseDTO toResponseDTO(Pauta pauta) {

        return new PautaResponseDTO(
                pauta.getId(),
                pauta.getTitulo(),
                pauta.getDescricao(),
                pauta.getAssembleia() != null ? new AssembleiaResponseDTO(
                        pauta.getAssembleia().getId(),
                        pauta.getAssembleia().getNome(),
                        pauta.getAssembleia().getDataCriacao(),
                        null
                ) : null
        );
    }
}
