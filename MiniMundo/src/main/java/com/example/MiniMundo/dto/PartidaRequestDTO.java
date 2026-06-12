package com.example.MiniMundo.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PartidaRequestDTO {
    private LocalDateTime dataPartida;
    private String estadio;
    private String faseCompeticao;
    private String placar;
    private List<Long> selecaoIds;
}