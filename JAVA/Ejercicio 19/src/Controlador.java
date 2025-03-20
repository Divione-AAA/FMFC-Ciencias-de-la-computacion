import java.util.ArrayList;

public class Controlador {

    ArrayList<Figura> lista = new ArrayList<Figura>();

    public void add(Figura t){
        lista.add(t);
    }
    public void del(Figura t){
        int u=lista.indexOf(t);
        lista.remove(u);
    }
    public double total(){
        double t=0;
        for(Figura f: lista){
            t+=f.volumen;
        }
        return t;
    }
}
