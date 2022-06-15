package sptech.projetocontinuada03.entidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class NewHeroiTest {

    @Test
    @DisplayName("Campo 'nome' deve estar anotado com @NotBlank")
    void nomeNotBlank() throws NoSuchFieldException {
        Class<Heroi> classe = Heroi.class;
        Field campoNome = classe.getDeclaredField("nome");
        assertTrue(campoNome.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("Teste de anotação max e min")
    void apelidosSize()throws NoSuchFieldException{
        Class<Heroi> classe = Heroi.class;

        Field campoNome = classe.getDeclaredField("apelido");

        Size size = campoNome.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(2, size.min());
        assertEquals(20, size.max());
    }

}