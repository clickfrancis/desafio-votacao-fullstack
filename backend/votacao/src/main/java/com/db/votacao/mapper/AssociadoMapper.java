package com.db.votacao.mapper;

import com.db.votacao.model.Associado;
import com.db.votacao.model.Voto;
import com.db.votacao.model.dto.response.AssociadoResponseDTO;
import com.db.votacao.model.dto.response.VotoResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociadoMapper {

    final VotoMapper votoMapper;

    public AssociadoMapper(VotoMapper votoMapper) {
        this.votoMapper = votoMapper;
    }

    public AssociadoResponseDTO toResponseDTO(Associado associado) {

        List<Long> votoIds = associado.getVotos().stream()
                .map(Voto::getId)
                .toList();

        return new AssociadoResponseDTO(
                associado.getId(),
                associado.getNome(),
                associado.getCpf(),
                associado.getStatus(),
                votoIds
        );
    }

    public List<AssociadoResponseDTO> toListAssociadoResponseDTO(List<Associado> associados) {
        return associados.stream()
                .map(this::toResponseDTO) // Usa o método acima para converter cada Associado
                .collect(Collectors.toList());
    }
}
