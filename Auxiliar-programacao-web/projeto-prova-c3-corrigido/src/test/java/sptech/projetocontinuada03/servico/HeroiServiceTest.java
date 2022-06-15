package sptech.projetocontinuada03.servico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sptech.projetocontinuada03.entidade.ClasseHeroi;
import sptech.projetocontinuada03.entidade.Heroi;

import static org.junit.jupiter.api.Assertions.*;

class HeroiServiceTest {

    HeroiService service = new HeroiService();

//    @Test
    @DisplayName("Se herói null deve ocorrer uma IllegalArgumentException")
    void getSalarioHeroiNulo() {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            service.getSalario(null);
        });
        assertEquals("Herói é obrigatório", excecao.getMessage());
    }

    @Test
    @DisplayName("Se herói válido o salário deve ser o salário base da classe do herói mais um bônus de 100 por ponto que ele tem")
    void getSalarioHeroiValido() {

        ClasseHeroi classe = new ClasseHeroi();
        classe.setSalarioBase(5000.0);
        Heroi heroi1 = new Heroi();
        heroi1.setPontos(50);
        heroi1.setClasse(classe);
        Heroi heroi2 = new Heroi();
        heroi2.setPontos(1000);
        heroi2.setClasse(classe);

        double salario1 = service.getSalario(heroi1);
        double salario2 = service.getSalario(heroi2);

        assertEquals(classe.getSalarioBase() + (100 * heroi1.getPontos()), salario1);
        assertEquals(classe.getSalarioBase() + (100 * heroi2.getPontos()), salario2);
    }

}