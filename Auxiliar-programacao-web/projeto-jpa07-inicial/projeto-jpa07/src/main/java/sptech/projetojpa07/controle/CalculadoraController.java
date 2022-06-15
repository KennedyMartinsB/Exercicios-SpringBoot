package sptech.projetojpa07.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa07.servicos.CalculosService;

@RestController
@RequestMapping("/calculos")
public class CalculadoraController {
    private CalculosService service = new CalculosService();

    @GetMapping("/classe-social/{salario}")
    public ResponseEntity<String> getClasse(@PathVariable double salario){
        try{
            return ResponseEntity.status(200).body(service.getClasseSocial(salario));
        }catch (IllegalArgumentException exception){
            return ResponseEntity.status(400).body(exception.getMessage());
        }
    }

    /*
    @GetMapping("/inss/{salario}")
    public ResponseEntity<Double> exercicio(@PathVariable double salario){
        try{

        }catch (IllegalArgumentException exception){
            return ResponseEntity.status(400).body(exception.getMessage());
        }
    }
    */

}
