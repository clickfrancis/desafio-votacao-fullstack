package com.db.votacao.service;

import com.db.votacao.model.Associado;
import com.db.votacao.model.Enum.VotoOpcao;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Sessao;
import com.db.votacao.model.Voto;
import com.db.votacao.repository.AssociadoRepository;
import com.db.votacao.repository.PautaRepository;
import com.db.votacao.repository.SessaoRepository;
import com.db.votacao.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VotacaoService {

    @Autowired
    private final PautaRepository pautaRepository;
    @Autowired
    private final AssociadoRepository associadoRepository;
    @Autowired
    private final VotoRepository votoRepository;
    @Autowired
    private final SessaoRepository sessaoRepository;

    public Voto registrarVoto(Long pautaId, Long sessaoId, Long associadoId, VotoOpcao votoOpcao) {

        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        if (!sessao.sessaoEstaAberta(sessao)) {
            throw new RuntimeException("Sessão de votação está encerrada.");
        }

        Associado associado = associadoRepository.findById(associadoId)
                .orElseThrow(() -> new RuntimeException("Associado não encontrado"));

        Optional<Voto> votoExistente = votoRepository.findByPautaAndAssociado(pauta, associado);
        if (votoExistente.isPresent()) {
            throw new RuntimeException("O associado já votou nesta pauta");
        }

        Voto voto = new Voto(sessao, associado, votoOpcao);
        return votoRepository.save(voto);
    }

    public String contabilizarResultado(Long pautaId) {

        long votosSim = votoRepository.countByPautaAndVotoOpcao(pautaId, VotoOpcao.SIM);
        long votosNao = votoRepository.countByPautaAndVotoOpcao(pautaId, VotoOpcao.NAO);

        if (votosSim > votosNao) {
            return "A votação foi aprovada com " + votosSim + " votos a favor e " + votosNao + " votos contra.";
        } else if (votosNao > votosSim) {
            return "A votação foi rejeitada com " + votosNao + " votos contra e " + votosSim + " votos a favor.";
        } else {
            return "A votação empatou com " + votosSim + " votos a favor e " + votosNao + " votos contra.";
        }
    }
}
