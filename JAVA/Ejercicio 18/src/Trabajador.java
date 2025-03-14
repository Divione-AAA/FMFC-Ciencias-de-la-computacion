public class Trabajador {
    private final String nombre;
    private int dias;
    private double salario,salario_base;
    public Trabajador(String name,double salario_base, int dias){
        this.nombre = name;
        this.salario_base = salario_base;
        this.dias = dias;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public String getNombre() {
        return nombre;
    }
}

/*
 * clase sistema para saber el salario
 * nombre del trabajador
 * anadir y quitar,
 * economia para salario, RRHH para bajo
 * dias trabajados en el mes, salario basico
 * determine actores
 * */