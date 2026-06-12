package com.example.MiniMundo.jogador;

import com.example.MiniMundo.dto.JogadorRequestDTO;
import com.example.MiniMundo.selecao.Selecao;
import com.example.MiniMundo.selecao.SelecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final SelecaoRepository selecaoRepository;

    public Jogador salvar(JogadorRequestDTO dto) {
        Selecao selecao = selecaoRepository.findById(dto.getSelecaoId())
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada. ID: " + dto.getSelecaoId()));

        Jogador novoJogador = Jogador.builder()
                .nome(dto.getNome())
                .numeroCamisa(dto.getNumeroCamisa())
                .posicao(dto.getPosicao())
                .idade(dto.getIdade())
                .selecao(selecao)
                .build();

        return jogadorRepository.save(novoJogador);
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }

    public Jogador buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado. ID: " + id));
    }

    public List<Jogador> buscarPorSelecao(Long selecaoId) {
        return jogadorRepository.findBySelecaoId(selecaoId);
    }

    public Jogador atualizar(Long id, JogadorRequestDTO dto) {
        Jogador jogadorExistente = buscarPorId(id);

        Selecao novaSelecao = selecaoRepository.findById(dto.getSelecaoId())
                .orElseThrow(() -> new RuntimeException("Nova Seleção não encontrada. ID: " + dto.getSelecaoId()));

        jogadorExistente.setNome(dto.getNome());
        jogadorExistente.setNumeroCamisa(dto.getNumeroCamisa());
        jogadorExistente.setPosicao(dto.getPosicao());
        jogadorExistente.setIdade(dto.getIdade());
        jogadorExistente.setSelecao(novaSelecao);

        return jogadorRepository.save(jogadorExistente);
    }

    public void deletar(Long id) {
        Jogador jogador = buscarPorId(id);
        jogadorRepository.delete(jogador);
    }
}