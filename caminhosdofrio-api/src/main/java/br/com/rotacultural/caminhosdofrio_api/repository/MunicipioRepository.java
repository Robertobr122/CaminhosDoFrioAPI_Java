package br.com.rotacultural.caminhosdofrio_api.repository;

import br.com.rotacultural.caminhosdofrio_api.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//diz que a entidade é municipio e seu Id é long
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

    Optional<Municipio> findByNomeIgnoreCase(String nome);  //Busca municipio pelo nome e ignora maiusculo/minusculo 

} 