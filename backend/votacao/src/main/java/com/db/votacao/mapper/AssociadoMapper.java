package com.db.votacao.mapper;

import com.db.votacao.model.Associado;
import com.db.votacao.model.dto.response.AssociadoResponseDTO;
import com.db.votacao.model.dto.response.VotoResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssociadoMapper {

    final VotoMapper votoMapper;

    public AssociadoMapper(VotoMapper votoMapper) {
        this.votoMapper = votoMapper;
    }

    public AssociadoResponseDTO toResponseDTO(Associado associado) {

        List<VotoResponseDTO> votosResponseDTO = Optional.ofNullable(associado.getVotos())
                .orElse(Collections.emptyList())
                .stream()
                .map(votoMapper::toResponseDTO)
                .toList();

        return new AssociadoResponseDTO(
                associado.getId(),
                associado.getNome(),
                associado.getCpf(),
                associado.getStatus()
        );
    }

    public List<AssociadoResponseDTO> toListAssociadoResponseDTO(List<Associado> associados) {
        return associados.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
