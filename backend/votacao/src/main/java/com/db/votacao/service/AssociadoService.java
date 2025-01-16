package com.db.votacao.service;

import com.db.votacao.mapper.AssociadoMapper;
import com.db.votacao.model.Associado;
import com.db.votacao.model.Enum.StatusAssociado;
import com.db.votacao.model.dto.request.AssociadoRequestDTO;
import com.db.votacao.model.dto.response.AssociadoResponseDTO;
import com.db.votacao.repository.AssociadoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private AssociadoMapper associadoMapper;

    @Transactional
    public AssociadoResponseDTO cadastrarAssociado(AssociadoRequestDTO associadoRequestDTO) {

        if (associadoRepository.existsByCpf(associadoRequestDTO.cpf())) {
            throw new RuntimeException("Já existe um associado cadastrado com o CPF: " + associadoRequestDTO.cpf());
        }

        if (validarCpf(associadoRequestDTO.cpf())) {
            throw new RuntimeException("Cpf inválido: " + associadoRequestDTO.cpf());

        }

        Associado associado = new Associado();
        associado.setNome(associadoRequestDTO.nome());
        associado.setCpf(associadoRequestDTO.cpf());

        Associado associadoSalvo = associadoRepository.save(associado);

        return associadoMapper.toResponseDTO(associadoSalvo);
    }

    public List<AssociadoResponseDTO> listarTodos() {
        return associadoRepository.findAll().stream()
                .map(associadoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AssociadoResponseDTO obterAssociadoPorId(Long id) {
        Associado associado = associadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Associado não encontrado com ID: " + id));

        return associadoMapper.toResponseDTO(associado);
    }

    public  boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("[0-9]{11}");
    }
}
