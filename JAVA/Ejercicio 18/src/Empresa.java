import java.util.ArrayList;

public class Empresa {
    private ArrayList<Trabajador> lista = new ArrayList<Trabajador>();

    public void add(Trabajador t){
        lista.add(t);
    }

    public void del(Trabajador t){
        lista.remove(t);
    }

    public double salarioT(Trabajador t){
        int index = lista.indexOf(t);
        return lista.get(index).getSalario();
    }

    public double salarioTotal(){
        double total = 0.0;
        for(Trabajador i : lista){
            total+=i.getSalario();
        }
        return total;
    }
}
