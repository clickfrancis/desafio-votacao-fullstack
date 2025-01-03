package com.db.votacao.service;

import com.db.votacao.model.Associado;
import com.db.votacao.model.Enum.StatusAssociado;
import com.db.votacao.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado criarAssociado(Associado associado) {

//        public StatusAssociado verificarStatusVoto(String ) {
//
//            if (!validarCpf(cpf)) {
//                return StatusAssociado.UNABLE_TO_VOTE;
//            }
//        }

        return associadoRepository.save(associado);
    }

    public Associado obterAssociado(Long id) {
        return associadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Associado não encontrado com ID: " + id));
    }

//    public  boolean validarCpf(String cpf) {
//        return cpf != null && cpf.matches("[0-9]{11}");
//    }
}
