package sptech.projetojpa07.controle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraControllerTest {

    CalculadoraController controller = new CalculadoraController();

    @Test
    @DisplayName("getClasse() deveria obter a classe correta")
    void getClasseCenarioValido(){
        ResponseEntity<String> resposta = controller.getClasse(25000);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("A - rico", resposta.getBody());
    }

    @Test
    @DisplayName("getClasse() - retornar erro")
    void getClasseErro(){
        ResponseEntity<String> resposta = controller.getClasse(-50);

        assertEquals(400, resposta.getStatusCodeValue());
        assertEquals("salario invalido", resposta.getBody());
    }
}