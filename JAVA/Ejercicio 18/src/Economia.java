import java.util.ArrayList;

public class Economia {
    private double salario,salario_basico;

    public void setSalario_basico(double salario_basico) {
        this.salario_basico = salario_basico;
    }

    public void calcular(int dias, ArrayList<Trabajador> l, String t){
        this.salario=salario_basico*(double)(dias/24);
        for (Trabajador trabajador : l) {
            if (trabajador.getNombre().equals(t)) {
                trabajador.setSalario(salario);
                return;
            }
        }
    }
}
/*
* cardinalidad
*
* */