import java.util.ArrayList;
//import java.util.Vector;
public class Area_2 extends Area{

    public Area_2(String nombre,int indice,ArrayList<Trabajador> lista){
        super(nombre,indice,lista);
    }

    public void add(Trabajador t){
        lista.add(t);
    }

    private double pot(double a, int b){
        return b==0 ? a : a*pot(a,b-1);
    }

    public double getEficiencia(){
        /*Vector<Integer> j = new Vector<Integer>();
        j.*/
        double ef=0;
        for(Trabajador i: lista){
            ef+=i.getExperiencia();
        }
        return pot((2*indice/ef), lista.size()+1);
    }
}
