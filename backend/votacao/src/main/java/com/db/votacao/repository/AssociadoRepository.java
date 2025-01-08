package com.db.votacao.repository;

import com.db.votacao.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    boolean existsByCpf(String cpf);
}
