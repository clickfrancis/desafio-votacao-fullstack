package com.db.votacao.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.CascadeType;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "pautas")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "assembleia_id", nullable = false)
    private Assembleia assembleia;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    private List<Sessao> sessoesVotacao;
}
