package sptech.projetojpa07.servicos;

public class CalculosService {

    public double calcularInss(Double salarioBruto){
        if(salarioBruto < 500){
            throw new IllegalArgumentException("Salário deve ser mais de 500");
        }
        Double inss = salarioBruto <= 2000 ? 0.05 : 0.10;
        return salarioBruto*inss;
    }

    public boolean receberAuxilio(double salario, int dependentes){
        boolean result;
        if (salario < 500 || dependentes < 0) {
            throw new IllegalArgumentException("O salario deve ser a partir de 500 e os dependentes a partir de 0");
        }
        if(salario < 2000 || (dependentes > 3 && salario < 4000)){
            return true;
        }
        return false;
    }

    public String getClasseSocial(double salario){

        if(salario < 0){
            throw new IllegalArgumentException("salario invalido");
        }

        if(salario < 1000){
            return "D - muito pobre";
        } else if (salario < 2500) {
            return "C - pobre";
        } else if (salario < 7000) {
            return "B - classe media";
        }else{
            return  "A - rico";
        }
    }

}
