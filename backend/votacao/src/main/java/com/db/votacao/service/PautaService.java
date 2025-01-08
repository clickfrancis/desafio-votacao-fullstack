package com.db.votacao.service;

import com.db.votacao.mapper.PautaMapper;
import com.db.votacao.model.Assembleia;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Voto;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import com.db.votacao.model.dto.response.PautaResponseDTO;
import com.db.votacao.model.dto.response.ResultadoVotacaoResponseDTO;
import com.db.votacao.repository.AssembleiaRepository;
import com.db.votacao.repository.PautaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public PautaResponseDTO cadastrarPauta(PautaRequestDTO pautaRequestDTO) {
        Pauta pauta = pautaMapper.toEntity(pautaRequestDTO);

        if (pautaRequestDTO.assembleiaId() != null) {
            Assembleia assembleia = assembleiaRepository.findById(pautaRequestDTO.assembleiaId())
                    .orElseThrow(() -> new RuntimeException("Assembleia não encontrada com ID: " + pautaRequestDTO.assembleiaId()));
            pauta.setAssembleia(assembleia);
        }

        return pautaMapper.toResponseDTO(pautaRepository.save(pauta));
    }

    @Transactional
    public PautaResponseDTO pautaWithAssembleia(Long pautaId, Long assembleiaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada com ID: " + pautaId));
        Assembleia assembleia = assembleiaRepository.findById(assembleiaId)
                .orElseThrow(() -> new RuntimeException("Assembleia não encontrada com ID: " + assembleiaId));

        pauta.setAssembleia(assembleia);

        return pautaMapper.toResponseDTO(pautaRepository.save(pauta));
    }

    public List<PautaResponseDTO> listarPautas() {
        List<Pauta> pautas = pautaRepository.findAll();
        return pautas.stream()
                .map(pautaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PautaResponseDTO listarPautaPorId(Long id) {
        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada com ID: " + id));
        return pautaMapper.toResponseDTO(pauta);
    }

    public ResultadoVotacaoResponseDTO contabilizarVotos(Long pautaId) {

        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada."));

        List<Voto> votos = pauta.getVotos();

        int votosSim = (int) votos.stream().filter(voto -> "SIM".equalsIgnoreCase(String.valueOf(voto.getVoto()))).count();
        int votosNao = (int) votos.stream().filter(voto -> "NAO".equalsIgnoreCase(String.valueOf(voto.getVoto()))).count();
        int totalVotos = votosSim + votosNao;

        String resultado = votosSim > votosNao ? "Aprovado" : "Reprovado";

        return new ResultadoVotacaoResponseDTO(
                pauta.getId(),
                pauta.getTitulo(),
                totalVotos,
                votosSim,
                votosNao,
                resultado
        );
    }
}
