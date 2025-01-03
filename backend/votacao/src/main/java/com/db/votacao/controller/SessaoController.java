package com.db.votacao.controller;

import com.db.votacao.model.Sessao;
import com.db.votacao.service.SessaoVotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vi/api/sessao-votacao")
@AllArgsConstructor
public class SessaoController {

    @Autowired
    private final SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    public ResponseEntity<Sessao> abrirSessaoVotacao(
            @RequestParam Long pautaId,
            @RequestParam(required = false) Integer tempoEmMinutos) {
        Sessao novaSessao = sessaoVotacaoService.abrirSessaoVotacao(pautaId, tempoEmMinutos);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSessao);
    }

    @GetMapping
    public ResponseEntity<List<Sessao>> listarTodasSessoes() {
        List<Sessao> sessoes = sessaoVotacaoService.listarTodasSessoes();
        return ResponseEntity.status(HttpStatus.OK).body(sessoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessao> buscarSessaoPorId(@PathVariable("id") Long sessaoId) {
        Sessao sessao = sessaoVotacaoService.buscarSessaoPorId(sessaoId);
        return ResponseEntity.status(HttpStatus.OK).body(sessao);
    }
}
