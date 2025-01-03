package com.db.votacao.repository;

import com.db.votacao.model.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {
}