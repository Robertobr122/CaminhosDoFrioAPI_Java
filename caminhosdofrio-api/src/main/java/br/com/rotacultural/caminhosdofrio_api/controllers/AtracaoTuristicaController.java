package br.com.rotacultural.caminhosdofrio_api.controllers;

import br.com.rotacultural.caminhosdofrio_api.model.AtracaoTuristica;
import br.com.rotacultural.caminhosdofrio_api.repository.AtracaoTuristicaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atracoes")
public class AtracaoTuristicaController {

    private final AtracaoTuristicaRepository atracaoRepository;

    public AtracaoTuristicaController(AtracaoTuristicaRepository atracaoRepository) {
        this.atracaoRepository = atracaoRepository;
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
    public AtracaoTuristica cadastrar(@RequestBody AtracaoTuristica atracao) {
        return atracaoRepository.save(atracao);
    }
}
