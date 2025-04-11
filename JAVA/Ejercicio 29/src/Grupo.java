import java.util.ArrayList;

public class Grupo {
    private ArrayList<Estudiante> lista = new ArrayList<>();
    private String nombre;

    public Grupo(String n) throws Exception{
        if(n.length()==0) throw new Exception("Usuario invalido");
        this.nombre = n;
    }

    public void addEstudiante(Estudiante t) throws Exception{
        if(lista.contains(t)) throw new Exception("Ya esta en el aula");
        lista.add(t);
    }

    public Double promedioAula() throws Exception{
        if(lista.isEmpty()) throw new Exception("Esta vacia el aula");
        Double t = 0.0;
        Estudiante f = new Estudiante(".");
        for(Estudiante i : lista){
            if((i.getLista().size() != f.getLista().size()) && (f.getLista().size()>0)){
                throw new Exception("La cantidad de asignaturas de los estudiantes no coinciden");
            }
            f = i;
            t+=i.getPromedio();
        }
        return t/lista.size();
    }
}
