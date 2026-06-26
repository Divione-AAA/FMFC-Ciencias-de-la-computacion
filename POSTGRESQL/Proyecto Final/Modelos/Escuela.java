package Modelos;

public class Escuela {

    private int codigoEscuela;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;

    public Escuela(int codigoEscuela, String nombre, String direccion, String telefono, String tipo){
        this.codigoEscuela = codigoEscuela;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public int getCodigoEscuela(){
        return codigoEscuela;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getTipo(){
        return tipo;
    }
}