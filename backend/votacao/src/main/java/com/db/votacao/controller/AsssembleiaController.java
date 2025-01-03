package com.db.votacao.controller;

import com.db.votacao.model.Assembleia;
import com.db.votacao.service.AssembleiaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/assembleias")
@AllArgsConstructor
public class AsssembleiaController {

    @Autowired
    private AssembleiaService assembleiaService;

    @PostMapping
    public ResponseEntity<Assembleia> criarAssembleia(@RequestBody Assembleia assembleia) {
        Assembleia novaAssembleia = assembleiaService.criarAssembleia(assembleia);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembleia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assembleia> obterAssembleia(@PathVariable Long id) {
        Assembleia assembleia = assembleiaService.obterAssembleia(id);
        return ResponseEntity.status(HttpStatus.OK).body(assembleia);
    }

    @GetMapping
    public ResponseEntity<List<Assembleia>> listarAssembleias() {
        List<Assembleia> assembleias = assembleiaService.listarAssembleias();
        return ResponseEntity.status(HttpStatus.OK).body(assembleias);
    }
}
