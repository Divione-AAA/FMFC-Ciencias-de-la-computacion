import java.util.*;

public class ordenamiento {
    private static void burbuja(ArrayList<Integer> lista) {
        for (int i = 0; i < lista.size()-1; i++) {
            for (int j = 0; j < lista.size()-1; j++) {
                if (lista.get(j) > lista.get(j+1)) {
                    Integer temp = lista.get(j);
                    lista.set(j,j+1);
                    lista.set(j+1,temp);
                }
            }
        }
    }
    private static void seleccion(ArrayList<Integer> lista){
        for(int i=0;i<lista.size()-1;i++){
            Integer min=i;
            for(int j=0;j<lista.size();j++){
                if(lista.get(j)<lista.get(min)){
                    min = j;
                }
            }
        Integer temp = lista.get(i);
        lista.set(i,min);
        lista.set(min,temp);
        }
    }

    public static void insercion(ArrayList<Integer> lista){
        for(int i=0;i<lista.size();i++){
            int t = lista.get(i);
            int j =i - 1;
            while(j>=0 && lista.get(j)>t){
                lista.set(j+1,j);
                j--;
            }
            lista.set(j+1,t);
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> j =  new ArrayList<>();
        j.add(1);
        j.add(5);
        j.add(3);
        burbuja(j);
        seleccion(j);
        insercion(j);
    }
}
