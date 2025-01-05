package com.db.votacao.mapper;

import com.db.votacao.model.Voto;
import com.db.votacao.model.dto.response.VotoResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VotoMapper {

    public VotoResponseDTO toResponseDTO(Voto voto) {

        return new VotoResponseDTO(
                voto.getId(),
                voto.getVoto(),
                voto.getSessao().getId(),
                voto.getPauta().getId(),
                voto.getAssociado().getId()
        );
    }

    public List<VotoResponseDTO> toListVotoResponseDTO(List<Voto> votos) {
        return votos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
