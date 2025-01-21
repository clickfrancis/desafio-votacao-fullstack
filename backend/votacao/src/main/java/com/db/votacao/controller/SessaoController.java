package com.db.votacao.controller;

import com.db.votacao.model.Sessao;
import com.db.votacao.model.dto.response.SessaoResponseDTO;
import com.db.votacao.service.SessaoVotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/sessao-votacao")
@AllArgsConstructor
public class SessaoController {

    @Autowired
    private final SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    public ResponseEntity<SessaoResponseDTO> abrirSessaoVotacao(
            @RequestParam Long pautaId,
            @RequestParam(required = false) Integer tempoEmMinutos) {
        SessaoResponseDTO novaSessao = sessaoVotacaoService.abrirSessaoVotacao(pautaId, tempoEmMinutos);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSessao);
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDTO>> listarTodasSessoes() {
        List<SessaoResponseDTO> sessoes = sessaoVotacaoService.listarTodasSessoes();
        return ResponseEntity.status(HttpStatus.OK).body(sessoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoResponseDTO> buscarSessaoPorId(@PathVariable("id") Long sessaoId) {
        SessaoResponseDTO sessao = sessaoVotacaoService.buscarSessaoPorId(sessaoId);
        return ResponseEntity.status(HttpStatus.OK).body(sessao);
    }

}
