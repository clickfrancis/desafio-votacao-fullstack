package com.db.votacao.controller;

import com.db.votacao.model.Voto;
import com.db.votacao.model.dto.request.VotoRequestDTO;
import com.db.votacao.model.dto.response.ResultadoVotacaoResponseDTO;
import com.db.votacao.model.dto.response.VotoResponseDTO;
import com.db.votacao.service.VotacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votos")
@AllArgsConstructor
public class VotoController {

    @Autowired
    private final VotacaoService votoService;

    @PostMapping
    public ResponseEntity<VotoResponseDTO> registrarVoto(@Valid @RequestBody VotoRequestDTO votoRequestDTO) {
        VotoResponseDTO voto = votoService.registrarVoto(votoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(voto);
    }

    @GetMapping
    public ResponseEntity<List<VotoResponseDTO>> listarTodosVotos() {
        List<VotoResponseDTO> votos = votoService.listarTodosVotos();
        return ResponseEntity.status(HttpStatus.OK).body(votos);
    }
}
