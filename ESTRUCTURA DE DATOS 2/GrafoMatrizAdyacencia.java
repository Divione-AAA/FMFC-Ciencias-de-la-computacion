import java.util.*;

public class GrafoMatrizAdyacencia implements Grafo {

    private List<String> vertices;
    private float[][] m;
    private int MAX = 100;

    public GrafoMatrizAdyacencia() {
        vertices = new ArrayList<>();
        m = new float[MAX][MAX];
    }

    // ========= MANIPULACION =========

    public int insVertice(String n) {
        if (vertices.contains(n)) return -1;
        vertices.add(n);
        return vertices.size()-1;
    }

    public void insArista(String o,String d,float p) {

        int i = buscar(o);
        int j = buscar(d);

        if (i==-1) i=insVertice(o);
        if (j==-1) j=insVertice(d);

        m[i][j]=p;
    }

    public void insArista(String o,String d) {
        insArista(o,d,1);
    }

    public void elimVertice(String n) {

        int idx = buscar(n);
        if (idx==-1) return;

        vertices.remove(idx);

        for(int i=idx;i<vertices.size();i++)
            for(int j=0;j<MAX;j++){
                m[i][j]=m[i+1][j];
                m[j][i]=m[j][i+1];
            }
    }

    public void elimArista(String o,String d) {

        int i=buscar(o);
        int j=buscar(d);

        if(i!=-1 && j!=-1)
            m[i][j]=0;
    }

    // ========= OPERACIONES =========

    public int buscar(String n) {
        return vertices.indexOf(n);
    }

    public boolean esAdyacente(String o,String d) {

        int i=buscar(o);
        int j=buscar(d);

        if(i==-1 || j==-1) return false;
        return m[i][j]!=0;
    }

    // ========= BFS =========

    public List<String> recorridoAmplitud(String o) {

        List<String> r=new ArrayList<>();
        int s=buscar(o);
        if(s==-1) return r;

        boolean[] vis=new boolean[vertices.size()];
        Queue<Integer> q=new LinkedList<>();

        q.add(s);
        vis[s]=true;

        while(!q.isEmpty()){
            int v=q.poll();
            r.add(vertices.get(v));

            for(int i=0;i<vertices.size();i++)
                if(m[v][i]!=0 && !vis[i]){
                    vis[i]=true;
                    q.add(i);
                }
        }
        return r;
    }

    // ========= DFS =========

    public List<String> recorridoProfundidad(String o){

        List<String> r=new ArrayList<>();
        int s=buscar(o);
        if(s==-1) return r;

        boolean[] vis=new boolean[vertices.size()];
        Stack<Integer> st=new Stack<>();

        st.push(s);

        while(!st.isEmpty()){
            int v=st.pop();

            if(!vis[v]){
                vis[v]=true;
                r.add(vertices.get(v));

                for(int i=vertices.size()-1;i>=0;i--)
                    if(m[v][i]!=0 && !vis[i])
                        st.push(i);
            }
        }
        return r;
    }

    // ========= CAMINO SIN PESO =========

    public void caminosMSinPeso(String o){

        int s=buscar(o);
        int n=vertices.size();

        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);

        Queue<Integer> q=new LinkedList<>();
        dist[s]=0;
        q.add(s);

        while(!q.isEmpty()){
            int v=q.poll();

            for(int i=0;i<n;i++)
                if(m[v][i]!=0 && dist[i]==Integer.MAX_VALUE){
                    dist[i]=dist[v]+1;
                    q.add(i);
                }
        }

        System.out.println(Arrays.toString(dist));
    }

    // ========= DIJKSTRA =========

    public boolean caminoMConPesoPositivo(String o){

        int s=buscar(o);
        int n=vertices.size();

        float[] dist=new float[n];
        boolean[] vis=new boolean[n];

        Arrays.fill(dist,Float.MAX_VALUE);
        dist[s]=0;

        for(int k=0;k<n;k++){

            int u=-1;
            float min=Float.MAX_VALUE;

            for(int i=0;i<n;i++)
                if(!vis[i] && dist[i]<min){
                    min=dist[i];
                    u=i;
                }

            if(u==-1) break;

            vis[u]=true;

            for(int v=0;v<n;v++)
                if(m[u][v]!=0){
                    float nueva=dist[u]+m[u][v];
                    if(nueva<dist[v]) dist[v]=nueva;
                }
        }

        System.out.println(Arrays.toString(dist));
        return true;
    }

    // ========= BELLMAN =========

    public boolean caminoMConPesosNegativos(String o){

        int s=buscar(o);
        int n=vertices.size();

        float[] dist=new float[n];
        Arrays.fill(dist,Float.MAX_VALUE);
        dist[s]=0;

        for(int k=1;k<n;k++)
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    if(m[i][j]!=0 && dist[i]!=Float.MAX_VALUE &&
                       dist[i]+m[i][j]<dist[j])
                        dist[j]=dist[i]+m[i][j];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(m[i][j]!=0 && dist[i]+m[i][j]<dist[j]){
                    System.out.println("Ciclo negativo");
                    return false;
                }

        System.out.println(Arrays.toString(dist));
        return true;
    }
}