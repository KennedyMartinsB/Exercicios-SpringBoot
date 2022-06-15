package sptech.projetocontinuada03.controle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sptech.projetocontinuada03.entidade.Heroi;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class HeroiControllerTest {

    Class<HeroiController> classe = HeroiController.class;

    @Test
    @DisplayName("HeroiController deve estar anotada com @RequestMapping(/herois)")
    void classeRequestMapping(){
        RequestMapping requestMapping = classe.getDeclaredAnnotation(RequestMapping.class);

        assertNotNull(requestMapping);
        assertEquals("/herois", requestMapping.value()[0]);
    }

    @Test
    @DisplayName("Teste PostMapping")
    void postAntacoes()throws Exception{
        Method postHeroi = classe.getDeclaredMethod("postHeroi", Heroi.class);

        Annotation[] anotacoesPrimeiroParametro = postHeroi.getParameterAnnotations()[0];
        assertTrue(postHeroi.isAnnotationPresent(PostMapping.class));
        assertEquals(RequestBody.class, anotacoesPrimeiroParametro[0].annotationType());
        assertEquals(Valid.class, anotacoesPrimeiroParametro[1].annotationType());
    }

}