package com.example.MiniMundo.jogador;

import com.example.MiniMundo.selecao.Selecao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer numeroCamisa;

    @Column(nullable = false)
    private String posicao;

    @Column(nullable = false)
    private Integer idade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "selecao_id", nullable = false)
    private Selecao selecao;
}