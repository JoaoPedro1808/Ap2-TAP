package com.example.MiniMundo.selecao;

import com.example.MiniMundo.dto.SelecaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selecoes")
@RequiredArgsConstructor
public class SelecaoController {

    private final SelecaoService service;

    @PostMapping
    @Operation(summary = "Cadastrar Seleção", description = "Adiciona uma nova seleção ao sistema utilizando um DTO limpo")
    public ResponseEntity<Selecao> cadastrar(@RequestBody SelecaoRequestDTO dto) {
        Selecao novaSelecao = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSelecao);
    }

    @GetMapping
    @Operation(summary = "Listar Seleções", description = "Retorna todas as seleções nacionais cadastradas")
    public ResponseEntity<List<Selecao>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/detalhes")
    @Operation(summary = "Buscar Seleção por ID", description = "Retorna os detalhes de uma seleção específica passando o ID como parâmetro")
    public ResponseEntity<Selecao> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar Seleção", description = "Modifica os dados cadastrais de uma seleção existente")
    public ResponseEntity<Selecao> atualizar(@RequestParam Long id, @RequestBody SelecaoRequestDTO dto) {
        Selecao selecaoAtualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(selecaoAtualizada);
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Deletar Seleção", description = "Remove uma seleção do banco de dados")
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}