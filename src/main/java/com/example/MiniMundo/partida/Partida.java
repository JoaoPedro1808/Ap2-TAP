package com.example.MiniMundo.partida;

import com.example.MiniMundo.selecao.Selecao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataPartida;

    @Column(nullable = false)
    private String estadio;

    @Column(nullable = false)
    private String faseCompeticao;

    private String placar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "partida_selecao",
            joinColumns = @JoinColumn(name = "partida_id"),
            inverseJoinColumns = @JoinColumn(name = "selecao_id")
    )
    private List<Selecao> selecoes;
}