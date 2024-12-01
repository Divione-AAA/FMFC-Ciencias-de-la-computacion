import java.io.*;

public class Persona implements Serializable {

    private static final long serialVersionUID=1L;
    String nombre;
    String  carnet;
    int edad;

    public Persona(String n, String c, int e){
        this.nombre=n;
        this.carnet=c;
        this.edad=e;
    }

    public String getCarnetIdentidad(){
        return carnet;
    }

    @Override
    public String toString(){
        return "Persona{" + "nombre= "+ nombre+ "edad: "+ "carnet: " +carnet + '/';
    }
}
