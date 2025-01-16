package com.db.votacao.controller;

import com.db.votacao.model.Assembleia;
import com.db.votacao.model.dto.request.AssembleiaRequestDTO;
import com.db.votacao.model.dto.response.AssembleiaResponseDTO;
import com.db.votacao.service.AssembleiaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/assembleia")
@AllArgsConstructor
public class AsssembleiaController {

    @Autowired
    private AssembleiaService assembleiaService;

    @PostMapping
    public ResponseEntity<AssembleiaResponseDTO> criarAssembleia(@RequestBody AssembleiaRequestDTO assembleia) {
        AssembleiaResponseDTO novaAssembleia = assembleiaService.criarAssembleia(assembleia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAssembleia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssembleiaResponseDTO> obterAssembleia(@PathVariable Long id) {
        AssembleiaResponseDTO assembleia = assembleiaService.obterAssembleia(id);
        return ResponseEntity.status(HttpStatus.OK).body(assembleia);
    }

    @GetMapping
    public ResponseEntity<List<AssembleiaResponseDTO>> listarAssembleias() {
        List<AssembleiaResponseDTO> assembleias = assembleiaService.listarAssembleias();
        return ResponseEntity.status(HttpStatus.OK).body(assembleias);
    }
}
