package com.db.votacao.repository;

import com.db.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
