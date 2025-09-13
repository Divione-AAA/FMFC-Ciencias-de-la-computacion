import java.util.ArrayList;

public class ArbolPair<E extends Comparable<E>>{
    private static class Pair<K,V>{
        K first;
        V second;

        Pair(K k, V v){
            this.first = k;
            this.second = v;
        }
    }

    private final ArrayList<Pair<E,Integer>> lista;
    private int tam;

    public ArbolPair(){
        lista = new ArrayList<>(100000);
        tam = 0;
    }

    public void insertHijo(E padre, E elemento)throws Exception {
        if(tam == 0){
            lista.add(new Pair<>(elemento, -1));
            ++tam;
            return;
        }
        else{
            for(int i = 0; i < tam; i++){
                if(lista.get(i).first.compareTo(padre) == 0){
                    lista.add(new Pair<>(elemento, i));
                    ++tam;
                    return;
                }
            }
        }
        throw new Exception("No existe ese padre");
    }

    public int buscar(E elemento)throws Exception {

        for(int i = 0; i < tam; i++){
            if(lista.get(i).first.compareTo(elemento) == 0){
                return(lista.get(i).second);
            }
        }

        throw new Exception("No existe ese elemento");

    }

    public void insertarHermano(E padre, E hermano)throws Exception {
        int i = buscar(padre);
        lista.add(new Pair<>(hermano,i));
    }
}

