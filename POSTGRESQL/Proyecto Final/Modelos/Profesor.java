package Modelos;

public class Profesor {

    private String ci;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private int edad;
    private String sexo;
    private String colorPiel;
    private String direccionParticular;
    private String telefonoParticular;
    private String municipio;
    private String integracionPolitica;
    private String centroGraduacion;
    private int anioGraduacion;
    private int anioInicioTrabajo;
    private String procedencia;
    private String categoriaDocente;
    private String categoriaCientifica;
    private boolean misionInternacionalista;
    private boolean esCuadro;
    private String cargo;
    private String nivelEnsenanza;
    private boolean seSupera;
    private String ultimaEvaluacionProfesional;

    public Profesor(String ci, String nombre1, String nombre2, String apellido1, String apellido2, int edad,
                    String sexo, String colorPiel, String direccionParticular, String telefonoParticular, String municipio,
                    String integracionPolitica, String centroGraduacion, int anioGraduacion, int anioInicioTrabajo,
                    String procedencia, String categoriaDocente, String categoriaCientifica, boolean misionInternacionalista,
                    boolean esCuadro, String cargo, String nivelEnsenanza, boolean seSupera,
                    String ultimaEvaluacionProfesional) {
        this.ci = ci;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.sexo = sexo;
        this.colorPiel = colorPiel;
        this.direccionParticular = direccionParticular;
        this.telefonoParticular = telefonoParticular;
        this.municipio = municipio;
        this.integracionPolitica = integracionPolitica;
        this.centroGraduacion = centroGraduacion;
        this.anioGraduacion = anioGraduacion;
        this.anioInicioTrabajo = anioInicioTrabajo;
        this.procedencia = procedencia;
        this.categoriaDocente = categoriaDocente;
        this.categoriaCientifica = categoriaCientifica;
        this.misionInternacionalista = misionInternacionalista;
        this.esCuadro = esCuadro;
        this.cargo = cargo;
        this.nivelEnsenanza = nivelEnsenanza;
        this.seSupera = seSupera;
        this.ultimaEvaluacionProfesional = ultimaEvaluacionProfesional;
    }

    public String getCi() {
        return ci;
    }

    public String getNombreCompleto() {
        return nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
    }

    public String getCategoriaDocente() {
        return categoriaDocente;
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

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getColorPiel() {
        return colorPiel;
    }

    public String getDireccionParticular() {
        return direccionParticular;
    }

    public String getTelefonoParticular() {
        return telefonoParticular;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getIntegracionPolitica() {
        return integracionPolitica;
    }

    public String getCentroGraduacion() {
        return centroGraduacion;
    }

    public int getAnioGraduacion() {
        return anioGraduacion;
    }

    public int getAnioInicioTrabajo() {
        return anioInicioTrabajo;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public String getCategoriaCientifica() {
        return categoriaCientifica;
    }

    public boolean isMisionInternacionalista() {
        return misionInternacionalista;
    }

    public boolean isEsCuadro() {
        return esCuadro;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNivelEnsenanza() {
        return nivelEnsenanza;
    }

    public boolean isSeSupera() {
        return seSupera;
    }

    public String getUltimaEvaluacionProfesional() {
        return ultimaEvaluacionProfesional;
    }
}