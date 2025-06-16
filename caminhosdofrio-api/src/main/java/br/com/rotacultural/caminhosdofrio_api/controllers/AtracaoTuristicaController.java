package br.com.rotacultural.caminhosdofrio_api.controllers;

import br.com.rotacultural.caminhosdofrio_api.model.AtracaoTuristica;
import br.com.rotacultural.caminhosdofrio_api.repository.AtracaoTuristicaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.rotacultural.caminhosdofrio_api.repository.EventoRepository;
import br.com.rotacultural.caminhosdofrio_api.model.Evento;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/atracoes")
public class AtracaoTuristicaController {

    private final AtracaoTuristicaRepository atracaoRepository;
    private final EventoRepository eventoRepository;

    public AtracaoTuristicaController(AtracaoTuristicaRepository atracaoRepository, EventoRepository eventoRepository) {
        this.atracaoRepository = atracaoRepository;
        this.eventoRepository = eventoRepository;
    }

    @GetMapping
    public List<AtracaoTuristica> listarTodas() {
        return atracaoRepository.findAll();
    }

    @GetMapping("/evento/{eventoId}")
    public List<AtracaoTuristica> listarPorEvento(@PathVariable Long eventoId) {
        return atracaoRepository.findByEventoId(eventoId);
    }

    @PostMapping
    public ResponseEntity<AtracaoTuristica> cadastrar(@RequestBody AtracaoTuristica atracao) {
        Long eventoId = atracao.getEvento().getId();

        Optional<Evento> evento = eventoRepository.findById(eventoId);
        if (evento.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        atracao.setEvento(evento.get());
        AtracaoTuristica salva = atracaoRepository.save(atracao);
        return ResponseEntity.ok(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtracaoTuristica> atualizar(@PathVariable Long id,
            @RequestBody AtracaoTuristica novaAtracao) {
        return atracaoRepository.findById(id)
                .map(atracaoExistente -> {
                    atracaoExistente.setNome(novaAtracao.getNome());
                    atracaoExistente.setDescricao(novaAtracao.getDescricao());
                    atracaoExistente.setTipo(novaAtracao.getTipo());
                    atracaoExistente.setImagemUrl(novaAtracao.getImagemUrl());

                    // Atualiza o evento apenas se o ID for válido
                    if (novaAtracao.getEvento() != null && novaAtracao.getEvento().getId() != null) {
                        eventoRepository.findById(novaAtracao.getEvento().getId())
                                .ifPresent(atracaoExistente::setEvento);
                    }

                    AtracaoTuristica atualizada = atracaoRepository.save(atracaoExistente);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtracao(@PathVariable Long id) {
        atracaoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
