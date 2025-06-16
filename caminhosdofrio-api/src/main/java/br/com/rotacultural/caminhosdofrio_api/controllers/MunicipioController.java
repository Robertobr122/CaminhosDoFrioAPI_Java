package br.com.rotacultural.caminhosdofrio_api.controllers;

import br.com.rotacultural.caminhosdofrio_api.model.Municipio;
import org.springframework.web.bind.annotation.*;
import br.com.rotacultural.caminhosdofrio_api.repository.MunicipioRepository;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    private final MunicipioRepository municipioRepository;

    public MunicipioController(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @GetMapping
    public List<Municipio> listarTodos() {
        return municipioRepository.findAll();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Municipio> buscarPorNome(@PathVariable String nome) {
        return municipioRepository.findByNomeIgnoreCase(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Municipio cadastrar(@RequestBody Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    @PutMapping("/municipios/{id}")
    public ResponseEntity<Municipio> atualizar(@PathVariable Long id, @RequestBody Municipio novoMunicipio) {
        return municipioRepository.findById(id)
                .map(municipioExistente -> {
                    municipioExistente.setNome(novoMunicipio.getNome());
                    municipioExistente.setDescricao(novoMunicipio.getDescricao());
                    municipioExistente.setEstado(novoMunicipio.getEstado());
                    municipioExistente.setImagemUrl(novoMunicipio.getImagemUrl());
                    Municipio atualizado = municipioRepository.save(municipioExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/municipios/{id}")
    public ResponseEntity<Void> deletarMunicipio(@PathVariable Long id) {
        municipioRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}
