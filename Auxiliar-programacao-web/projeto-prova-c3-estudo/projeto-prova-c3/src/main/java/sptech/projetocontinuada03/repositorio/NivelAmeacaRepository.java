package sptech.projetocontinuada03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.projetocontinuada03.entidade.NivelAmeaca;

public interface NivelAmeacaRepository extends JpaRepository<NivelAmeaca, Long> {

    Integer findByPontuacao(Long id);

}
