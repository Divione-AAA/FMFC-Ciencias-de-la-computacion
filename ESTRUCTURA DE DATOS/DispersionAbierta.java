import java.util.LinkedList;

public class DispersionAbierta<E extends Hashable>{
    LinkedList<E>[] vector;
    public DispersionAbierta(){
        vector = new LinkedList[10000];
        for(int i=0;i<10000;i++){
            vector[i] = new LinkedList<>();
        }
        vector = null;
    }
    private int hashing(E e){
        return e.hash()%10000;
    }
    public int buscarPos(E e){
        return e.hash()%10000;
    }
    public void insertar(E e)throws Exception{
        int p = hashing(e);
        for(int i=0;i<vector[p].size();i++){
            if(vector[p].get(i).compareTo(e)==0){
                throw new Exception("Elemento duplicado");
            }
        }
        vector[p].addLast(e);
    }
    public void borrar(E e)throws Exception{
        int p = hashing(e);
        for(int i=0;i<vector[p].size();i++){
            if(vector[p].get(i).compareTo(e)==0){
                vector[p].remove(e);
                return;
            }
        }
        throw new Exception("Elemento jamas encontrado");
    }
}
