package com.db.votacao.mapper;

import com.db.votacao.model.Assembleia;
import com.db.votacao.model.Sessao;
import com.db.votacao.model.dto.request.SessaoRequestDTO;
import com.db.votacao.model.dto.response.SessaoResponseDTO;
import com.db.votacao.model.dto.response.VotoResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessaoMapper {


    final VotoMapper votoMapper;

    public SessaoMapper(
            VotoMapper votoMapper
    ) {

        this.votoMapper = votoMapper;
    }

    public SessaoResponseDTO toResponseDTO(Sessao sessao) {

        List<VotoResponseDTO> votos = sessao.getVotos().stream()
                .map(votoMapper::toResponseDTO)
                .toList();

        return new SessaoResponseDTO(
                sessao.getId(),
                sessao.getInicio(),
                sessao.getFim(),
                null,
                votos
        );
    }

    public List<SessaoResponseDTO> toListSessaoResponseDTO(List<Sessao> sessoes) {
        return sessoes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
