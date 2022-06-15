package sptech.projetocontinuada03.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetocontinuada03.entidade.ClasseHeroi;
import sptech.projetocontinuada03.entidade.Heroi;
import sptech.projetocontinuada03.entidade.NivelAmeaca;
import sptech.projetocontinuada03.repositorio.ClasseHeroiRepository;
import sptech.projetocontinuada03.repositorio.HeroiRepository;
import sptech.projetocontinuada03.repositorio.NivelAmeacaRepository;
import sptech.projetocontinuada03.servico.HeroiService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    @Autowired
    private HeroiRepository heroiRepository;

    @Autowired
    private ClasseHeroiRepository classeHeroiRepository;

    @Autowired
    private NivelAmeacaRepository nivelAmeacaRepository;

    @Autowired
    private HeroiService heroiService;

    @Transactional
    @Modifying
    @PostMapping
    public ResponseEntity cadastrarHeroi(@RequestBody Heroi heroi) {
        heroi.setNome(heroi.getNome());
        heroi.setApelido(heroi.getApelido());
        heroi.setSalario(heroiService.getSalario(heroi));
        heroi.setPontos(heroi.getPontos());
        heroi.setClasse(classeHeroiRepository.save(heroi.getClasse()));
        heroiRepository.save(heroi);
        return ResponseEntity.status(201).build();
    }

    @Transactional
    @Modifying
    @PutMapping("/{id}")
    public ResponseEntity alteraHeroi(@RequestBody Heroi heroi, @PathVariable Long id) {
        if (heroiRepository.existsById(id)) {
            heroi.setId(id);
            heroi.setNome(heroi.getNome());
            heroi.setApelido(heroi.getApelido());
            heroi.setSalario(heroiService.getSalario(heroi));
            heroi.setPontos(heroi.getPontos());
            heroi.setClasse(classeHeroiRepository.save(heroi.getClasse()));
            heroiRepository.save(heroi);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<Heroi>> getInfoHerois() {
        return ResponseEntity.status(200).body(heroiRepository.findAll());
    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<List<Heroi>> getHerois(@PathVariable String classe) {
        return ResponseEntity.status(200).body(heroiRepository.findByClasseNome(classe));
    }

    @PostMapping("/vitoria/{idHeroi}/{idNivelAmeaca}")
    public ResponseEntity patchVitoria(@RequestBody Heroi heroi, @PathVariable Long idHeroi,
                                       @PathVariable Long idNivelAmeaca) {

        if (heroiRepository.existsById(idHeroi) && nivelAmeacaRepository.existsById(idNivelAmeaca)){
            heroi.setPontos(heroi.getPontos() + nivelAmeacaRepository.findByPontuacao(idNivelAmeaca));
            heroiRepository.save(heroi);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }
}
