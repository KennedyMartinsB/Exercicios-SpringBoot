package sptech.projetocontinuada03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetocontinuada03.entidade.Heroi;

import java.util.List;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {
    List<Heroi> findByClasseNome(String nomeClasse);
}
