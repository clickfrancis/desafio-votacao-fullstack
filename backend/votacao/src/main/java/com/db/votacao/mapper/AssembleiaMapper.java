package com.db.votacao.mapper;

import com.db.votacao.model.Assembleia;
import com.db.votacao.model.dto.request.AssembleiaRequestDTO;
import com.db.votacao.model.dto.response.AssembleiaResponseDTO;
import com.db.votacao.model.dto.response.PautaResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssembleiaMapper {

    private final PautaMapper pautaMapper;

    public AssembleiaMapper(PautaMapper pautaMapper) {
        this.pautaMapper = pautaMapper;
    }

    public Assembleia toEntity(AssembleiaRequestDTO assembleiaRequestDTO) {
        Assembleia assembleia = new Assembleia();
        assembleia.setNome(assembleiaRequestDTO.nome());
        assembleia.setDataCriacao(LocalDateTime.now());
        return assembleia;
    }

    public AssembleiaResponseDTO toResponseDTO(Assembleia assembleia) {

        List<PautaResponseDTO> pautaResponseDTOs = Optional.ofNullable(assembleia.getPautas())
                .orElse(Collections.emptyList())
                .stream()
                .map(pautaMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new AssembleiaResponseDTO(
                assembleia.getId(),
                assembleia.getNome(),
                assembleia.getDataCriacao(),
                pautaResponseDTOs
        );
    }
}
