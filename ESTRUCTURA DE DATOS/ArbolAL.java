import java.util.*;

public class ArbolAL<E>{
    private static class Pair<K, V> {
        K first;
        V second;

        Pair(K k, V v) {
            this.first = k;
            this.second = v;
        }
    }
    private ArrayList<Pair<E, ArrayList<Integer>>> lista;
}
