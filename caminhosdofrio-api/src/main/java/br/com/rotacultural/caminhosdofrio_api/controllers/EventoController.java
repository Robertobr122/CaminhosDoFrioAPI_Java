package br.com.rotacultural.caminhosdofrio_api.controllers;

import br.com.rotacultural.caminhosdofrio_api.model.Evento;
import br.com.rotacultural.caminhosdofrio_api.repository.EventoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;
    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
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
    public Evento cadastrar(@RequestBody Evento evento) {
        return eventoRepository.save(evento);
    }
}