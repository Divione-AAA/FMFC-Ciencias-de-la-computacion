import java.util.ArrayList;

public abstract class  Area {
    protected String nombre;
    protected int indice;
    protected double eficiencia;
    protected ArrayList<Trabajador> lista = new ArrayList<Trabajador>();

    public Area(String nombre,int indice,ArrayList<Trabajador> l){
        this.nombre = nombre;
        this.indice = indice;
        this.lista = l;
    }
}
