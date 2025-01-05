package com.db.votacao.repository;

import com.db.votacao.model.Associado;
import com.db.votacao.model.Enum.VotoOpcao;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByPautaAndAssociado(Pauta pauta, Associado associado);

   // long countByPautaAndVotoOpcao(Long pautaId, String votoOpcao);
}
