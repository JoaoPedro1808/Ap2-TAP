package com.example.MiniMundo.dto;

import lombok.Data;

@Data
public class JogadorRequestDTO {
    private String nome;
    private Integer numeroCamisa;
    private String posicao;
    private Integer idade;
    private Long selecaoId;
}