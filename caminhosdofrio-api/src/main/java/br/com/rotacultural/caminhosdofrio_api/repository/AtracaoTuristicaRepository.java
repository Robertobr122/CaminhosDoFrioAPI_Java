package br.com.rotacultural.caminhosdofrio_api.repository;

import br.com.rotacultural.caminhosdofrio_api.model.AtracaoTuristica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtracaoTuristicaRepository extends JpaRepository<AtracaoTuristica, Long> {

    // Lista atrações de um evento específico
    List<AtracaoTuristica> findByEventoId(Long eventoId);
}
