import java.util.ArrayList;
public class Main{

    static void dfs(int nodo, ArrayList<ArrayList<Integer>> matrizAdy, boolean[] visitados) {

        //System.out.println(nodo + " ");
        if(visitados[nodo]) return;
        visitados[nodo] = true;
        
        for(Integer i: matrizAdy.get(nodo)){
            System.out.println(nodo + " " + i);
            dfs(i, matrizAdy, visitados);
        }
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> matrizAdy = new ArrayList<>();

        for(int i=0;i<6;i++){
            matrizAdy.add(new ArrayList<>());
        }

        matrizAdy.get(5).add(4);
        matrizAdy.get(4).add(1);
        matrizAdy.get(3).add(5);
        matrizAdy.get(1).add(3);
        matrizAdy.get(3).add(2);

        boolean[] visitados = new boolean[matrizAdy.size()];

        dfs(1, matrizAdy, visitados);
    }

    /*@Override
    public boolean equals(Object obj) {
        Main g = (Main) obj;
        return super.equals(obj);
    }*/
}