package Modelos;

public class Asignatura {

    private int codigoAsignatura;
    private String nombre;

    public Asignatura(int codigoAsignatura, String nombre) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}