package br.com.rotacultural.caminhosdofrio_api.controllers;

import br.com.rotacultural.caminhosdofrio_api.model.Evento;
import br.com.rotacultural.caminhosdofrio_api.model.Municipio;
import br.com.rotacultural.caminhosdofrio_api.repository.EventoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.rotacultural.caminhosdofrio_api.repository.MunicipioRepository;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final MunicipioRepository municipioRepository;
    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository, MunicipioRepository municipioRepository) {
        this.eventoRepository = eventoRepository;
        this.municipioRepository = municipioRepository;
    }

    @GetMapping
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/municipio/{municipioId}")
    public List<Evento> listarPorMunicipio(@PathVariable Long municipioId) {
        return eventoRepository.findByMunicipioId(municipioId);
    }

    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody Evento evento) {
        Long municipioId = evento.getMunicipio().getId();

        Optional<Municipio> municipio = municipioRepository.findById(municipioId);
        if (municipio.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        evento.setMunicipio(municipio.get());
        Evento salvo = eventoRepository.save(evento);

        return ResponseEntity.ok(salvo); 
    }

    @PutMapping("/eventos/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id, @RequestBody Evento novoEvento) {
        return eventoRepository.findById(id)
                .map(eventoExistente -> {
                    eventoExistente.setNome(novoEvento.getNome());
                    eventoExistente.setDescricao(novoEvento.getDescricao());
                    eventoExistente.setData(novoEvento.getData());
                    eventoExistente.setLocal(novoEvento.getLocal());
                    eventoExistente.setMunicipio(novoEvento.getMunicipio());
                    Evento atualizado = eventoRepository.save(eventoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}