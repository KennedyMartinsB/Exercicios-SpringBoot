package sptech.projetojpa07.servicos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculosServiceTest {

    @Test
    @DisplayName("calcularInss() deveria calculaar corretamente")
    void calcularInssCenarioValido() {
        // INSS deveria ser 5% se o salario for até 2000
        // ex: 1000 -> 50 / 2000 -> 100

        CalculosService service = new CalculosService();
        assertEquals(50, service.calcularInss(1000.0));
        assertEquals(100, service.calcularInss(2000.0));
        assertEquals(300, service.calcularInss(3000.0));
        assertEquals(500, service.calcularInss(5000.0));
    }

    @Test
    @DisplayName("calcularInss() deveria lançar exceção p/ salario < 500")
    void calcularInssDeveLancarErro(){
        CalculosService service = new CalculosService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularInss(499.99);
        });

       Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            service.calcularInss(-900.00);
        });

       assertEquals("Salário deve ser mais de 500", excecao.getMessage());
    }

    @Test
    @DisplayName("receberAuxilio() - deve calcular corretamente")
    void auxilio(){
        CalculosService service = new CalculosService();

        assertEquals(true,service.receberAuxilio(1900, 3));
        assertEquals(true,service.receberAuxilio(1000, 3));
        assertEquals(true,service.receberAuxilio(1900, 4));
        assertEquals(true,service.receberAuxilio(3000, 4));
        assertEquals(false,service.receberAuxilio(1900, 3));

        // Outra alternativa
        // assertTrue(service.receberAuxilio(1900, 3));



    }

    @Test
    @DisplayName("receberAuxilio() - deve retornar os erros")
    void auxilioExcecoes(){
        CalculosService service = new CalculosService();

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            service.receberAuxilio(200, -1);
        });

        assertEquals("O salario deve ser a partir de 500 e os dependentes a partir de 0", excecao.getMessage());
    }
}