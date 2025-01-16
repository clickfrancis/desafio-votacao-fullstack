package com.db.votacao.controller;

import com.db.votacao.model.Pauta;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import com.db.votacao.model.dto.response.PautaResponseDTO;
import com.db.votacao.model.dto.response.ResultadoVotacaoResponseDTO;
import com.db.votacao.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/pauta")
@AllArgsConstructor
public class PautaController {

    @Autowired
    private final PautaService pautaService;

    @PostMapping
    public ResponseEntity<PautaResponseDTO> cadastrarPauta(@RequestBody PautaRequestDTO pautaRequestDTO) {
        PautaResponseDTO pautaResponseDTO = pautaService.cadastrarPauta(pautaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaResponseDTO);
    }

    @PatchMapping("/{pautaId}/with/{assembleiaId}")
    public ResponseEntity<PautaResponseDTO> vincularAssembleia(
            @PathVariable Long pautaId,
            @PathVariable Long assembleiaId) {
        PautaResponseDTO pautaResponseDTO = pautaService.pautaWithAssembleia(pautaId, assembleiaId);
        return ResponseEntity.status(HttpStatus.OK).body(pautaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PautaResponseDTO>> listarPautas() {
        List<PautaResponseDTO> pautaResponseDTO = pautaService.listarPautas();
        return ResponseEntity.status(HttpStatus.OK).body(pautaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaResponseDTO> listarPautaPorId(@PathVariable Long id) {
        PautaResponseDTO pautaResponseDTO = pautaService.listarPautaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pautaResponseDTO);
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<ResultadoVotacaoResponseDTO> obterResultadoVotacao(@PathVariable Long id) {
        ResultadoVotacaoResponseDTO resultado = pautaService.contabilizarVotos(id);
        return ResponseEntity.ok(resultado);
    }
}
