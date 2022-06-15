package sptech.projetocontinuada03.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetocontinuada03.entidade.ClasseHeroi;
import sptech.projetocontinuada03.entidade.Heroi;
import sptech.projetocontinuada03.entidade.NivelAmeaca;
import sptech.projetocontinuada03.repositorio.ClasseHeroiRepository;
import sptech.projetocontinuada03.repositorio.HeroiRepository;
import sptech.projetocontinuada03.repositorio.NivelAmeacaRepository;
import sptech.projetocontinuada03.servico.HeroiService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

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

    @GetMapping
    public ResponseEntity<List<Heroi>> getHerois() {
        List<Heroi> lista = heroiRepository.findAll();
        return lista.isEmpty() ? status(204).build() : status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Void> postHeroi(@RequestBody @Valid Heroi novoHeroi) {
        if (!classeHeroiRepository.existsById(novoHeroi.getClasse().getId())) {
            return status(404).build();
        }
        novoHeroi.setClasse(classeHeroiRepository.findById(novoHeroi.getClasse().getId()).get());
        novoHeroi.setSalario(heroiService.getSalario(novoHeroi));
        heroiRepository.save(novoHeroi);
        return status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putHeroi(@PathVariable long id, @RequestBody Heroi heroi) {
        if (!heroiRepository.existsById(id)) {
            return status(404).build();
        }
        heroi.setId(id);
        heroi.setClasse(classeHeroiRepository.findById(heroi.getClasse().getId()).get());
        heroi.setSalario(heroiService.getSalario(heroi));
        heroiRepository.save(heroi);
        return status(200).build();
    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<List<Heroi>> getHerois(String classe) {
        List<Heroi> lista = heroiRepository.findByClasseNome(classe);
        return lista.isEmpty() ? status(204).build() : status(200).body(lista);
    }

    @PatchMapping("/vitoria/{idHeroi}/{idNivelAmeaca}")
    public ResponseEntity patchVitoria(@PathVariable long idHeroi,
                                       @PathVariable long idNivelAmeaca) {
        if (!heroiRepository.existsById(idHeroi)) {
            return status(404).build();
        }
        if (!nivelAmeacaRepository.existsById(idNivelAmeaca)) {
            return status(404).build();
        }

        Heroi heroi = heroiRepository.findById(idHeroi).get();
        NivelAmeaca nivelAmeaca = nivelAmeacaRepository.findById(idNivelAmeaca).get();
        ClasseHeroi classe =
                classeHeroiRepository.findTop1ByPontuacaoMinimaLessThanEqualOrderByPontuacaoMinimaDesc(heroi.getPontos());

        heroi.setPontos(heroi.getPontos() + nivelAmeaca.getPontuacao());
        if (heroi.getPontos() >= classe.getPontuacaoMinima()) {
            heroi.setClasse(classe);
        }

        heroi.setSalario(heroiService.getSalario(heroi));

        heroiRepository.save(heroi);

        return status(200).build();
    }
}
