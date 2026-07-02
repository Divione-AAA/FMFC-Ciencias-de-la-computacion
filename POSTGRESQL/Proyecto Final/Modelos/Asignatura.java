package Modelos;

public class Asignatura {

    private int idAsignatura;
    private String codigoAsignatura;
    private String nombre;

    public Asignatura(int idAsignatura, String codigoAsignatura, String nombre) {
        this.idAsignatura = idAsignatura;
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}