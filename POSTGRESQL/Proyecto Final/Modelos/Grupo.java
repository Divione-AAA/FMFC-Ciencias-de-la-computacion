package Modelos;

public class Grupo {

    private int idGrupo;
    private String modalidad;
    private String ambito;
    private String nivel;
    private String curso;
    private String grupo;
    private int codigoEscuela;

    public Grupo(int idGrupo,String modalidad,String ambito,String nivel,String curso,String grupo,int codigoEscuela){
        this.idGrupo = idGrupo;
        this.modalidad = modalidad;
        this.ambito = ambito;
        this.nivel = nivel;
        this.curso = curso;
        this.grupo = grupo;
        this.codigoEscuela = codigoEscuela;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getCodigoEscuela() {
        return codigoEscuela;
    }

    public void setCodigoEscuela(int codigoEscuela) {
        this.codigoEscuela = codigoEscuela;
    }
}