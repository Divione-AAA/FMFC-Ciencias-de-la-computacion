import java.util.ArrayList;

public class Estudiante {
    private ArrayList<Double> lista = new ArrayList<>();
    private String nombre;
    private Double promedio;

    public Estudiante(String nombre) throws Exception{
        if(nombre.length()==0) throw new Exception("Nombre invalido");
        this.nombre = nombre;
    }

    public void addNota(Double t) throws Exception{
        if(t<2) throw new Exception("Nota invalida");
        lista.add(t);
    }

    public void promedio() throws Exception{
        if(lista.size()==0) throw new Exception("Lista vacia");
        Double t=0.0;
        for (Double i: lista){
            t+=i;
        }
        this.promedio = t/lista.size();
    }

    public Double getPromedio() throws Exception {
        if(promedio<2 || promedio>5) throw new Exception("El promedio no es valido");
        return promedio;
    }

    public ArrayList<Double> getLista() {
        return lista;
    }
}
