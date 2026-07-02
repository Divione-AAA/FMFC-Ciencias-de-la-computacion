package Modelos;

public class Grupo {

    private int idGrupo;
    private String codigoGrupo;
    private String nombre;
    private int escuelaId;

    public Grupo(int idGrupo, String codigoGrupo, String nombre, int escuelaId) {
        this.idGrupo = idGrupo;
        this.codigoGrupo = codigoGrupo;
        this.nombre = nombre;
        this.escuelaId = escuelaId;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEscuelaId() {
        return escuelaId;
    }

    public void setEscuelaId(int escuelaId) {
        this.escuelaId = escuelaId;
    }

    public int getCodigoEscuela() {
        return escuelaId;
    }

    public void setCodigoEscuela(int escuelaId) {
        this.escuelaId = escuelaId;
    }
}