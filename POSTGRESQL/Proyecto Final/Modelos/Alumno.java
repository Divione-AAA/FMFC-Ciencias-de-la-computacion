package Modelos;

import java.util.Date;

public class Alumno {

    private String ci;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private String sexo;
    private String colorPiel;
    private String municipio;
    private String consejoPopular;
    private String grado;
    private String regimen;
    private String sesion;
    private String estadoAlumno;
    private String especialidad;
    private String procedenciaSocialPadre;
    private String procedenciaSocialMadre;
    private String direccion;
    private String telefono;
    private int codigoGrupo;

    public Alumno(String ci,String nombre1,String nombre2,String apellido1,String apellido2,Date fechaNacimiento,String sexo,String colorPiel,String municipio,String consejoPopular,String grado,String regimen,String sesion,String estadoAlumno,String especialidad,String procedenciaSocialPadre,String procedenciaSocialMadre,String direccion,String telefono,int codigoGrupo){

        this.ci = ci;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.colorPiel = colorPiel;
        this.municipio = municipio;
        this.consejoPopular = consejoPopular;
        this.grado = grado;
        this.regimen = regimen;
        this.sesion = sesion;
        this.estadoAlumno = estadoAlumno;
        this.especialidad = especialidad;
        this.procedenciaSocialPadre = procedenciaSocialPadre;
        this.procedenciaSocialMadre = procedenciaSocialMadre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoGrupo = codigoGrupo;
    }

    public String getCi() {
        return ci;
    }

    public String getNombre1() {
        return nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getColorPiel() {
        return colorPiel;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getConsejoPopular() {
        return consejoPopular;
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

    public String getEstadoAlumno() {
        return estadoAlumno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getProcedenciaSocialPadre() {
        return procedenciaSocialPadre;
    }

    public String getProcedenciaSocialMadre() {
        return procedenciaSocialMadre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getCodigoGrupo() {
        return codigoGrupo;
    }
}