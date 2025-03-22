import java.util.ArrayList;

public class Area_1 extends Area{

    public Area_1(String nombre,int indice,ArrayList<Trabajador> lista){
        super(nombre,indice,lista);
    }

    public void add(Trabajador t){
        lista.add(t);
    }

    private double pot(double a, int b){
        return b==0 ? a : a*pot(a,b-1);
    }

    public double getEficiencia(){
        double ef=0;
        for(Trabajador i: lista){
            ef+=i.getExperiencia();
        }
        return pot((indice/ef), lista.size());
    }
}
