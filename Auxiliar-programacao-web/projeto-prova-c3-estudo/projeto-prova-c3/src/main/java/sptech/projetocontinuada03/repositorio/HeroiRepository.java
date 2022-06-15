package sptech.projetocontinuada03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetocontinuada03.entidade.Heroi;

import java.util.List;
import java.util.Optional;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    Optional<Heroi> findById(Long id);

    List<Heroi> findByClasseNome(String classe);
}
