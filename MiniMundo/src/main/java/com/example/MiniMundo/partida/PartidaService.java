package com.example.MiniMundo.partida;

import com.example.MiniMundo.dto.PartidaRequestDTO;
import com.example.MiniMundo.selecao.Selecao;
import com.example.MiniMundo.selecao.SelecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final SelecaoRepository selecaoRepository;

    public Partida salvar(PartidaRequestDTO dto) {
        // Procura todas as seleções correspondentes aos IDs enviados no DTO
        List<Selecao> selecoesParticipantes = selecaoRepository.findAllById(dto.getSelecaoIds());

        if (selecoesParticipantes.isEmpty()) {
            throw new RuntimeException("Nenhuma seleção válida encontrada para os IDs fornecidos.");
        }

        Partida novaPartida = Partida.builder()
                .dataPartida(dto.getDataPartida())
                .estadio(dto.getEstadio())
                .faseCompeticao(dto.getFaseCompeticao())
                .placar(dto.getPlacar())
                .selecoes(selecoesParticipantes)
                .build();

        return partidaRepository.save(novaPartida);
    }

    public List<Partida> listarTodas() {
        return partidaRepository.findAll();
    }

    public Partida buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada. ID: " + id));
    }

    public Partida atualizar(Long id, PartidaRequestDTO dto) {
        Partida partidaExistente = buscarPorId(id);
        List<Selecao> novasSelecoes = selecaoRepository.findAllById(dto.getSelecaoIds());

        if (novasSelecoes.isEmpty()) {
            throw new RuntimeException("Nenhuma seleção válida encontrada para atualização.");
        }

        partidaExistente.setDataPartida(dto.getDataPartida());
        partidaExistente.setEstadio(dto.getEstadio());
        partidaExistente.setFaseCompeticao(dto.getFaseCompeticao());
        partidaExistente.setPlacar(dto.getPlacar());
        partidaExistente.setSelecoes(novasSelecoes);

        return partidaRepository.save(partidaExistente);
    }

    public void deletar(Long id) {
        Partida partida = buscarPorId(id);
        partidaRepository.delete(partida);
    }
}