package com.db.votacao.service;

import com.db.votacao.model.Pauta;
import com.db.votacao.model.Sessao;
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

    @Transactional
    public Sessao abrirSessaoVotacao(Long pautaId, Integer tempoEmMinutos) {

        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        Sessao sessaoVotacao = new Sessao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.verificaTempoFechamentoSessao(tempoEmMinutos);
        return sessaoRepository.save(sessaoVotacao);
    }

    public List<Sessao> listarTodasSessoes() {
        return sessaoRepository.findAll();
    }

    public Sessao buscarSessaoPorId(Long sessaoId) {
        return sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada com o ID: " + sessaoId));
    }
}
