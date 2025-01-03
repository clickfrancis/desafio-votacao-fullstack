package com.db.votacao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "assembleias")
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "assembleia", cascade = CascadeType.ALL)
    private List<Pauta> pautas;
}
