package com.db.votacao.model;

import com.db.votacao.model.Enum.StatusAssociado;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Random;

@Data
@Entity
@Table(name = "associados")
@NoArgsConstructor
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssociado status;

    @OneToMany(mappedBy = "associado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos;

    private StatusAssociado determinarStatusVotoAleatorio() {
        Random random = new Random();
        return random.nextBoolean() ? StatusAssociado.ABLE_TO_VOTE : StatusAssociado.UNABLE_TO_VOTE;
    }
}
