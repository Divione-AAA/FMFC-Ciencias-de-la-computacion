import java.util.*;

public class GrafoListaAdyacencia implements Grafo {

    private Map<String, List<Arista>> vertices;

    private static class Arista {
        String destino;
        float peso;

        Arista(String d, float p) {
            destino = d;
            peso = p;
        }
    }

    public GrafoListaAdyacencia() {
        vertices = new HashMap<>();
    }

    // ================= MANIPULACION =================

    public int insVertice(String nombre) {
        if (vertices.containsKey(nombre)) return -1;
        vertices.put(nombre, new ArrayList<>());
        return 1;
    }

    public void insArista(String o, String d, float p) {
        if (!vertices.containsKey(o)) insVertice(o);
        if (!vertices.containsKey(d)) insVertice(d);
        vertices.get(o).add(new Arista(d, p));
    }

    public void insArista(String o, String d) {
        insArista(o, d, 1);
    }

    public void elimVertice(String n) {
        vertices.remove(n);
        for (List<Arista> l : vertices.values())
            l.removeIf(a -> a.destino.equals(n));
    }

    public void elimArista(String o, String d) {
        if (vertices.containsKey(o))
            vertices.get(o).removeIf(a -> a.destino.equals(d));
    }

    // ================= OPERACIONES =================

    public int buscar(String n) {
        return vertices.containsKey(n) ? 1 : -1;
    }

    public boolean esAdyacente(String o, String d) {
        if (!vertices.containsKey(o)) return false;
        for (Arista a : vertices.get(o))
            if (a.destino.equals(d)) return true;
        return false;
    }

    // ================= RECORRIDOS =================

    public List<String> recorridoAmplitud(String o) {

        List<String> res = new ArrayList<>();
        if (!vertices.containsKey(o)) return res;

        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        q.add(o);
        vis.add(o);

        while (!q.isEmpty()) {
            String v = q.poll();
            res.add(v);

            for (Arista a : vertices.get(v))
                if (!vis.contains(a.destino)) {
                    vis.add(a.destino);
                    q.add(a.destino);
                }
        }
        return res;
    }

    public List<String> recorridoProfundidad(String o) {

        List<String> res = new ArrayList<>();
        if (!vertices.containsKey(o)) return res;

        Set<String> vis = new HashSet<>();
        Stack<String> st = new Stack<>();

        st.push(o);

        while (!st.isEmpty()) {
            String v = st.pop();
            if (!vis.contains(v)) {
                vis.add(v);
                res.add(v);

                List<Arista> ady = vertices.get(v);
                for (int i = ady.size() - 1; i >= 0; i--)
                    st.push(ady.get(i).destino);
            }
        }
        return res;
    }

    // ================= CAMINO MIN SIN PESO =================

    public void caminosMSinPeso(String o) {

        Map<String,Integer> dist = new HashMap<>();
        Queue<String> q = new LinkedList<>();

        for (String v : vertices.keySet())
            dist.put(v, Integer.MAX_VALUE);

        dist.put(o, 0);
        q.add(o);

        while (!q.isEmpty()) {
            String v = q.poll();

            for (Arista a : vertices.get(v))
                if (dist.get(a.destino) == Integer.MAX_VALUE) {
                    dist.put(a.destino, dist.get(v) + 1);
                    q.add(a.destino);
                }
        }

        System.out.println(dist);
    }

    // ================= DIJKSTRA =================

    public boolean caminoMConPesoPositivo(String o) {

        Map<String,Float> dist = new HashMap<>();
        Set<String> vis = new HashSet<>();

        for (String v : vertices.keySet())
            dist.put(v, Float.MAX_VALUE);

        dist.put(o, 0f);

        while (vis.size() < vertices.size()) {

            String u = null;
            float min = Float.MAX_VALUE;

            for (String v : vertices.keySet())
                if (!vis.contains(v) && dist.get(v) < min) {
                    min = dist.get(v);
                    u = v;
                }

            if (u == null) break;

            vis.add(u);

            for (Arista a : vertices.get(u)) {

                float nueva = dist.get(u) + a.peso;
                if (nueva < dist.get(a.destino))
                    dist.put(a.destino, nueva);
            }
        }

        System.out.println(dist);
        return true;
    }

    // ================= BELLMAN =================

    public boolean caminoMConPesosNegativos(String o) {

        Map<String,Float> dist = new HashMap<>();

        for (String v : vertices.keySet())
            dist.put(v, Float.MAX_VALUE);

        dist.put(o, 0f);

        int n = vertices.size();

        for (int k = 1; k < n; k++)
            for (String u : vertices.keySet())
                for (Arista a : vertices.get(u))
                    if (dist.get(u) != Float.MAX_VALUE &&
                        dist.get(u) + a.peso < dist.get(a.destino))
                        dist.put(a.destino, dist.get(u) + a.peso);

        for (String u : vertices.keySet())
            for (Arista a : vertices.get(u))
                if (dist.get(u) + a.peso < dist.get(a.destino)) {
                    System.out.println("Ciclo negativo");
                    return false;
                }

        System.out.println(dist);
        return true;
    }
}