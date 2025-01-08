package com.db.votacao.service;

import com.db.votacao.mapper.VotoMapper;
import com.db.votacao.model.Associado;
import com.db.votacao.model.Enum.StatusAssociado;
import com.db.votacao.model.Enum.VotoOpcao;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Sessao;
import com.db.votacao.model.Voto;
import com.db.votacao.model.dto.request.VotoRequestDTO;
import com.db.votacao.model.dto.response.VotoResponseDTO;
import com.db.votacao.repository.AssociadoRepository;
import com.db.votacao.repository.PautaRepository;
import com.db.votacao.repository.SessaoRepository;
import com.db.votacao.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private final VotoMapper votoMapper;

    public VotoResponseDTO registrarVoto(VotoRequestDTO votoRequestDTO) {

        Pauta pauta = pautaRepository.findById(votoRequestDTO.pautaId())
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        Sessao sessao = sessaoRepository.findById(votoRequestDTO.sessaoId())
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        if (!sessao.sessaoEstaAberta(sessao)) {
            throw new RuntimeException("Sessão de votação está encerrada.");
        }

        Associado associado = associadoRepository.findById(votoRequestDTO.associadoId())
                .orElseThrow(() -> new RuntimeException("Associado não encontrado"));

        Optional<Voto> votoExistente = votoRepository.findByPautaAndAssociado(pauta, associado);
        if (votoExistente.isPresent()) {
            throw new RuntimeException("O associado já votou nesta pauta");
        }

        if (associado.determinarStatusVotoAleatorio() == StatusAssociado.UNABLE_TO_VOTE) {
            throw new RuntimeException("O associado não pode votar");
        }

        Voto voto = new Voto(sessao, associado, votoRequestDTO.votoOpcao());
        return votoMapper.toResponseDTO(votoRepository.save(voto));
    }

    public List<VotoResponseDTO> listarTodosVotos() {
        List<Voto> votos = votoRepository.findAll();
        return votos.stream()
                .map(voto -> new VotoResponseDTO(
                        voto.getId(),
                        voto.getVoto(),
                        voto.getSessao().getId(),
                        voto.getPauta().getId(),
                        voto.getAssociado().getId()))
                .collect(Collectors.toList());
    }
}
