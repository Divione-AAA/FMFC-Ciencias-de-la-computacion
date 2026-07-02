package Modelos;

public class Imparte {

    private int idImparte;
    private String idProfesor;
    private int idGrupo;
    private int idAsignatura;

    public Imparte(
            int idImparte,
            String idProfesor,
            int idGrupo,
            int idAsignatura
    ) {

        this.idImparte = idImparte;
        this.idProfesor = idProfesor;
        this.idGrupo = idGrupo;
        this.idAsignatura = idAsignatura;
    }

    public int getIdImparte() {
        return idImparte;
    }

    public void setIdImparte(int idImparte) {
        this.idImparte = idImparte;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public String toString() {
        return "Imparte{" +
                "idImparte=" + idImparte +
                ", idProfesor='" + idProfesor + '\'' +
                ", idGrupo=" + idGrupo +
                ", idAsignatura=" + idAsignatura +
                '}';
    }
}