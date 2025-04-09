import java.util.ArrayList;
public class Persona {
    private ArrayList<String> lista = new ArrayList<>();
    private String nombre;

    public Persona(String s){
        this.nombre = s;
    }

    public void addAmigo(String t)throws Exception{
        if(lista.contains(t)) throw new Exceptions("Ya existe " + t);
        lista.add(t);
    }

    public void delAmigo(String t) throws Exception{
        if(!lista.contains(t)){
            throw new Exceptions("No existe");
        }else{
            lista.remove(t);
        }
    }

    public String nombreDe(Integer t) throws Exception{
        if(lista.size() == 0) throw new Exceptions("Lista vacia");
        if(t > lista.size()-1 || t < 0){
            throw new Exceptions("Fuera de limites");
        }else{
            return lista.get(t);
        }
        //funciona parecido, pero funciona
        /*try{
            return lista.get(t);
        }catch (Exception e){
            throw e;
        }*/
    }
}
