package com.db.votacao.service;

import com.db.votacao.mapper.SessaoMapper;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Sessao;
import com.db.votacao.model.dto.response.SessaoResponseDTO;
import com.db.votacao.repository.PautaRepository;
import com.db.votacao.repository.SessaoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessaoVotacaoService {

    @Autowired
    private final PautaRepository pautaRepository;
    @Autowired
    private final SessaoRepository sessaoRepository;
    @Autowired
    private final SessaoMapper sessaoMapper;

    @Transactional
    public Sessao abrirSessaoVotacao(Long pautaId, Integer tempoEmMinutos) {

        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        Sessao sessaoVotacao = new Sessao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.verificaTempoFechamentoSessao(tempoEmMinutos);
        return sessaoRepository.save(sessaoVotacao);
    }

    public List<SessaoResponseDTO> listarTodasSessoes() {
        List<Sessao> sessao = sessaoRepository.findAll();
        return sessaoMapper.toListSessaoResponseDTO(sessao);
    }

    public SessaoResponseDTO buscarSessaoPorId(Long sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada com o ID: " + sessaoId));
        return sessaoMapper.toResponseDTO(sessao);
    }
}
