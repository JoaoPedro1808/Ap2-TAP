package com.example.MiniMundo.selecao;

import com.example.MiniMundo.jogador.Jogador;
import com.example.MiniMundo.partida.Partida;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomePais;

    @Column(nullable = false)
    private String tecnico;

    @Column(nullable = false)
    private Integer rankingFifa;

    @OneToMany(mappedBy = "selecao", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Jogador> jogadores;

    @ManyToMany(mappedBy = "selecoes")
    @JsonIgnore
    private List<Partida> partidas;
}