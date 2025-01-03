package com.db.votacao.service;

import com.db.votacao.model.Assembleia;
import com.db.votacao.repository.AssembleiaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssembleiaService {

    @Autowired
    private AssembleiaRepository assembleiaRepository;

    @Transactional
    public Assembleia criarAssembleia(Assembleia assembleia) {
        return assembleiaRepository.save(assembleia);
    }

    public Assembleia obterAssembleia(Long id) {
        Optional<Assembleia> assembleia = assembleiaRepository.findById(id);
        if (assembleia.isEmpty()) {
            throw new RuntimeException("Assembleia não encontrada com ID: " + id);
        }
        return assembleia.get();
    }

    public List<Assembleia> listarAssembleias() {
        return assembleiaRepository.findAll();
    }
}
