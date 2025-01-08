package com.db.votacao.controller;

import com.db.votacao.model.dto.request.AssociadoRequestDTO;
import com.db.votacao.model.dto.response.AssociadoResponseDTO;
import com.db.votacao.service.AssociadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/associados")
@AllArgsConstructor
public class AssociadoController {

    @Autowired
    final AssociadoService associadoService;


    @PostMapping
    public ResponseEntity<AssociadoResponseDTO> cadastrarAssociado(@RequestBody AssociadoRequestDTO associadoRequestDTO) {
        AssociadoResponseDTO associadoResponseDTO = associadoService.cadastrarAssociado(associadoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(associadoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponseDTO>> listarTodos() {
        List<AssociadoResponseDTO> associados = associadoService.listarTodos();
        return ResponseEntity.ok(associados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoResponseDTO> buscarPorId(@PathVariable Long id) {
        AssociadoResponseDTO associadoResponseDTO = associadoService.obterAssociadoPorId(id);
        return ResponseEntity.ok(associadoResponseDTO);
    }
}
