package com.db.votacao.mapper;

import com.db.votacao.model.Assembleia;
import com.db.votacao.model.dto.request.AssembleiaRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssembleiaMapper {

    public Assembleia toEntity(AssembleiaRequestDTO assembleiaRequestDTO) {
        Assembleia assembleia = new Assembleia();
        assembleia.setNome(assembleiaRequestDTO.nome());
        assembleia.setDataCriacao(LocalDateTime.now());
        return assembleia;
    }
}
