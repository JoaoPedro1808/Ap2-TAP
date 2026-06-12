package com.example.MiniMundo.partida;

import com.example.MiniMundo.dto.PartidaRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService service;

    @PostMapping
    @Operation(summary = "Cadastrar Partida", description = "Regista uma nova partida vinculando as seleções participantes por ID")
    public ResponseEntity<Partida> cadastrar(@RequestBody PartidaRequestDTO dto) {
        Partida novaPartida = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPartida);
    }

    @GetMapping
    @Operation(summary = "Listar Partidas", description = "Retorna o histórico de todas as partidas registadas")
    public ResponseEntity<List<Partida>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/detalhes")
    @Operation(summary = "Buscar Partida por ID", description = "Retorna os detalhes estruturados de uma partida específica passando o ID como parâmetro")
    public ResponseEntity<Partida> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar Partida", description = "Modifica os dados de uma partida existente, como a atualização do placar")
    public ResponseEntity<Partida> atualizar(@RequestParam Long id, @RequestBody PartidaRequestDTO dto) {
        Partida partidaAtualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(partidaAtualizada);
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Deletar Partida", description = "Remove o registo de uma partida do sistema")
    public ResponseEntity<Void> deletar(@RequestParam Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}