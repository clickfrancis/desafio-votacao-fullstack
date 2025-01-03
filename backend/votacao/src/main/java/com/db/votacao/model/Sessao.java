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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sessao")
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime inicio = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @OneToMany
    @JoinColumn(name = "voto_id", nullable = false)
    private Voto voto;

    public void verificaTempoFechamentoSessao(Integer tempoSessao) {
        if (tempoSessao == null) {
            tempoSessao = 1;
        }
        int tempoEmSegundos = tempoSessao * 60;
        setFim(this.inicio.plusSeconds(tempoEmSegundos));
    }

    public boolean sessaoEstaAberta(Sessao sessao) {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(sessao.getInicio()) && agora.isBefore(sessao.getFim());
    }

}
