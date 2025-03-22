import java.util.ArrayList;

public class Trabajador {
    private String nombre;
    private int ano;
    private double experiencia;

    private ArrayList<Double> tareas = new ArrayList<Double>();

    private void eficiencia(){
        double exp = 0;
        for(Double i : tareas){
            exp+=i;
        }
        this.experiencia = (exp/tareas.size())/(ano);
    }

    public void addT(double t){
        tareas.add(t);
    }

    public double getExperiencia() {
        eficiencia();
        return experiencia;
    }
}
/*De cada empleado se tiene su nombre y año de comienzo de trabajo en la empresa.
 Para medir la eficiencia a cada trabajador se le asignan varias tareas en una semana
  y se computa el tiempo que le dedica a cada tarea. Como máximo se asignan 15 tareas
  a cada empleado. La eficiencia de un empleado se calcula buscando el promedio de los
  tiempos empleados (p) en cada una de las tareas y luego se calcula p*años trabajados.*/