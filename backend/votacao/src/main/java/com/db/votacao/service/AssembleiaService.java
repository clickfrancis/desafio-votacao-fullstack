package com.db.votacao.service;

import com.db.votacao.mapper.AssembleiaMapper;
import com.db.votacao.model.Assembleia;
import com.db.votacao.model.dto.request.AssembleiaRequestDTO;
import com.db.votacao.model.dto.response.AssembleiaResponseDTO;
import com.db.votacao.repository.AssembleiaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssembleiaService {

    @Autowired
    private AssembleiaRepository assembleiaRepository;
    @Autowired
    private AssembleiaMapper assembleiaMapper;

    @Transactional
    public AssembleiaResponseDTO criarAssembleia(AssembleiaRequestDTO assembleia) {
        Assembleia assembleiaEntity = assembleiaMapper.toEntity(assembleia);
        assembleiaRepository.save(assembleiaEntity);
       return assembleiaMapper.toResponseDTO(assembleiaEntity);

    }

    public AssembleiaResponseDTO obterAssembleia(Long id) {
        Assembleia assembleia = assembleiaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Assembleia não encontrada com ID: " + id));

        return assembleiaMapper.toResponseDTO(assembleia);
    }

    public List<AssembleiaResponseDTO> listarAssembleias() {

        List<Assembleia> assembleias = assembleiaRepository.findAll();
        return assembleias.stream()
                .map(assembleiaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
