package com.example.MiniMundo.jogador;

import com.example.MiniMundo.dto.JogadorRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    @Operation(summary = "Cadastrar Jogador", description = "Adiciona um novo jogador informando o ID da sua seleção")
    public ResponseEntity<Jogador> cadastrar(@RequestBody JogadorRequestDTO dto) {
        Jogador novoJogador = jogadorService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogador);
    }

    @GetMapping
    @Operation(summary = "Listar Jogadores", description = "Retorna todos os jogadores cadastrados")
    public ResponseEntity<List<Jogador>> listarTodos() {
        return ResponseEntity.ok(jogadorService.listarTodos());
    }

    @GetMapping("/detalhes")
    @Operation(summary = "Buscar Jogador por ID", description = "Retorna os detalhes de um jogador específico passando o ID como parâmetro")
    public ResponseEntity<Jogador> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(jogadorService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar Jogadores por Seleção", description = "Filtra os jogadores de uma seleção específica")
    public ResponseEntity<List<Jogador>> buscarPorSelecao(@RequestParam Long selecaoId) {
        return ResponseEntity.ok(jogadorService.buscarPorSelecao(selecaoId));
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar Jogador", description = "Atualiza os dados de um jogador existente")
    public ResponseEntity<Jogador> atualizar(@RequestParam Long id, @RequestBody JogadorRequestDTO dto) {
        Jogador jogadorAtualizado = jogadorService.atualizar(id, dto);
        return ResponseEntity.ok(jogadorAtualizado);
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Deletar Jogador", description = "Remove um jogador do sistema")
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        jogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}