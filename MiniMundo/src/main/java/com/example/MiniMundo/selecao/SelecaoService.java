package com.example.MiniMundo.selecao;

import com.example.MiniMundo.dto.SelecaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SelecaoService {

    private final SelecaoRepository repository;

    public Selecao salvar(SelecaoRequestDTO dto) {
        // Converte o DTO para a Entidade oficial do JPA
        Selecao novaSelecao = Selecao.builder()
                .nomePais(dto.getNomePais())
                .tecnico(dto.getTecnico())
                .rankingFifa(dto.getRankingFifa())
                .build();

        return repository.save(novaSelecao);
    }

    public List<Selecao> listarTodas() {
        return repository.findAll();
    }

    public Selecao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada. ID: " + id));
    }

    public Selecao atualizar(Long id, SelecaoRequestDTO dto) {
        Selecao selecaoExistente = buscarPorId(id);

        selecaoExistente.setNomePais(dto.getNomePais());
        selecaoExistente.setTecnico(dto.getTecnico());
        selecaoExistente.setRankingFifa(dto.getRankingFifa());

        return repository.save(selecaoExistente);
    }

    public void deletar(Long id) {
        Selecao selecao = buscarPorId(id);
        repository.delete(selecao);
    }
}