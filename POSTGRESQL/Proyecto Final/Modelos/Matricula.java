package Modelos;

import java.util.Date;

public class Matricula {

    private int id;

    private String ciAlumno;

    private String periodoAcademico;

    private Date fechaMatricula;

    private String condicionAcademica;

    private String grado;

    private String regimen;

    private String sesion;

    private String especialidad;

    private int codigoGrupo;


    public Matricula(
            int id,
            String ciAlumno,
            String periodoAcademico,
            Date fechaMatricula,
            String condicionAcademica,
            String grado,
            String regimen,
            String sesion,
            String especialidad,
            int codigoGrupo
    ) {

        this.id = id;
        this.ciAlumno = ciAlumno;
        this.periodoAcademico = periodoAcademico;
        this.fechaMatricula = fechaMatricula;
        this.condicionAcademica = condicionAcademica;
        this.grado = grado;
        this.regimen = regimen;
        this.sesion = sesion;
        this.especialidad = especialidad;
        this.codigoGrupo = codigoGrupo;
    }


    public int getId() {
        return id;
    }

    public String getCiAlumno() {
        return ciAlumno;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public String getCondicionAcademica() {
        return condicionAcademica;
    }

    public String getGrado() {
        return grado;
    }

    public String getRegimen() {
        return regimen;
    }

    public String getSesion() {
        return sesion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getCodigoGrupo() {
        return codigoGrupo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCiAlumno(String ciAlumno) {
        this.ciAlumno = ciAlumno;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public void setCondicionAcademica(String condicionAcademica) {
        this.condicionAcademica = condicionAcademica;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCodigoGrupo(int codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }


    @Override
    public String toString() {

        return "Matricula{" +
                "id=" + id +
                ", ciAlumno='" + ciAlumno + '\'' +
                ", periodoAcademico='" + periodoAcademico + '\'' +
                ", fechaMatricula=" + fechaMatricula +
                ", condicionAcademica='" + condicionAcademica + '\'' +
                ", grado='" + grado + '\'' +
                ", regimen='" + regimen + '\'' +
                ", sesion='" + sesion + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", codigoGrupo=" + codigoGrupo +
                '}';
    }

}