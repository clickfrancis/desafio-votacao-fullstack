package com.db.votacao.controller;

import com.db.votacao.model.Pauta;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import com.db.votacao.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vi/api/pautas")
@AllArgsConstructor
public class PautaController {

    @Autowired
    private final PautaService pautaService;

    @PostMapping("/{assembleiaId}")
    public ResponseEntity<Pauta> cadastrarPauta(
            @PathVariable Long assembleiaId,
            @RequestBody PautaRequestDTO pautaRequestDTO) {

        Pauta pauta = pautaService.cadastrarPauta(pautaRequestDTO, assembleiaId);

        return new ResponseEntity<>(pauta, HttpStatus.CREATED);
    }
}
