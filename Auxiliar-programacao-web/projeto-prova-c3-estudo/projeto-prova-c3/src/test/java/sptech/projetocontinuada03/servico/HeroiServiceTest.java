package sptech.projetocontinuada03.servico;

import org.junit.jupiter.api.Test;
import sptech.projetocontinuada03.entidade.Heroi;

import static org.junit.jupiter.api.Assertions.*;

class HeroiServiceTest {

    @Test
    void getSalarioErro() {
        assertEquals(null, "Herói é obrigatório.");
    }

    @Test
    void getSalario() {

        HeroiService heroiService = new HeroiService();
    }
}