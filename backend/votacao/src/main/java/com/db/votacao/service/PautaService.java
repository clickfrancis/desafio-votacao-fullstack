package com.db.votacao.service;

import com.db.votacao.mapper.PautaMapper;
import com.db.votacao.model.Assembleia;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import com.db.votacao.repository.AssembleiaRepository;
import com.db.votacao.repository.PautaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PautaService {

    @Autowired
    private final PautaRepository pautaRepository;
    @Autowired
    private final AssembleiaRepository assembleiaRepository;
    @Autowired
    private final PautaMapper pautaMapper;

    @Transactional
    public Pauta cadastrarPauta(PautaRequestDTO pautaRequestDTO, Long assembleiaId) {
        Assembleia assembleia = assembleiaRepository.findById(assembleiaId)
                .orElseThrow(() -> new RuntimeException("Assembleia não encontrada"));

        Pauta pauta = pautaMapper.toEntity(pautaRequestDTO, assembleia);

        return pautaRepository.save(pauta);
    }
}
