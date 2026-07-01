/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class GrafoMatriz_Ady implements Grafo {

    private static int TAMANHO_TABLA_INI = 10;
    private int numVertices;
    private float[][] matriz;
    private Vertice[] tabla;
    private float INFINITO = Float.MAX_VALUE;
    private String verticeOrigenActual;

    public GrafoMatriz_Ady() {
        numVertices = 0;
        matriz = new float[TAMANHO_TABLA_INI][TAMANHO_TABLA_INI];
        tabla = new Vertice[TAMANHO_TABLA_INI];
    }

    public int insVertice(String nombre) {
        int i = 0;
        while (i < numVertices) {
            if (tabla[i].getNombre().equals(nombre)) {
                return i;
            } else {
                i++;
            }
        }
        Vertice v1 = new Vertice(nombre);
        if (tabla.length == numVertices) {
            duplicarVectorTabla();
        }
        tabla[numVertices] = v1;
        return numVertices++;
    }

    private void duplicarVectorTabla() {
        Vertice[] nuevaTabla = new Vertice[tabla.length * 2];
        System.arraycopy(tabla, 0, nuevaTabla, 0, tabla.length);
        tabla = nuevaTabla;
        float[][] nuevamatriz = new float[matriz.length * 2][matriz.length * 2];
        for (int j = 0; j < matriz.length; j++) {
            System.arraycopy(matriz[j], 0, nuevamatriz[j], 0, matriz.length);
        }
        matriz = nuevamatriz;
    }

    public int buscar(String nombre) {
        int i = 0;
        while (i < numVertices) {
            if (tabla[i].getNombre().equals(nombre)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    public void insArista(String origen, String destino, float costo) {
        int origen2 = insVertice(origen);
        int destino2 = insVertice(destino);
        matriz[origen2][destino2] = costo;

    }

    public void insArista(String origen, String destino) {
        int origen2 = insVertice(origen);
        int destino2 = insVertice(destino);
        matriz[origen2][destino2] = 1;
    }

    public void elimArista(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        if (i != -1 && j != -1) {
            matriz[i][j] = 0;
        } else {
            System.out.println("la arista no existe");
        }
    }

    public void elimVertice(String nombre) {
        int i = buscar(nombre);
        if (i != -1) {
            if (i == numVertices - 1) //si es el �ltimo v�rtice lo �nico que tengo que hacer es decrementar la variable
            {
                numVertices--;
            } else {
                tabla[i] = tabla[numVertices - 1];
                numVertices--;
                for (int j = 0; j <= numVertices; j++) {
                    matriz[i][j] = matriz[numVertices][j]; // se copia la fila
                }
                for (int k = 0; k <= numVertices; k++) {
                    matriz[k][i] = matriz[k][numVertices];  //se copia la columna     
                }
            }
            for (int l = 0; l <= numVertices; l++) { // se ponen 0 en la fila y columna que se duplic�
                matriz[numVertices][l] = 0;
                matriz[l][numVertices] = 0;

            }
        } else {
            System.out.println("el v�rtice no existe");
        }
    }

    public boolean esAdyacente(String vertice1, String vertice2) {
        int i = buscar(vertice1);
        int j = buscar(vertice2);
        if (i != -1 && j != -1) {
            return matriz[i][j] != 0;
        } else {
            return false;
        }
    }

    public LinkedList recorrido_amplitud(String VerticeOrigen) {
        limpiarDatos();
        int verticeOrig = insVertice(VerticeOrigen);
        Queue<Integer> q = new LinkedList<>();
        LinkedList<String> L = new LinkedList<>();
        boolean repetir = true;
        while (repetir) {
            tabla[verticeOrig].setExtra(1);
            L.add(tabla[verticeOrig].getNombre());
            q.add(verticeOrig);
            while (!q.isEmpty()) {
                int verticeActual = q.remove();
                for (int i = 0; i < numVertices; i++) {
                    if (matriz[verticeActual][i] != 0 && tabla[i].getExtra() != 1) {
                        L.add(tabla[i].getNombre());
                        tabla[i].setExtra(1);
                        q.add(i);
                    }
                }
            }
            repetir = false;
            for (int i = 0; i < numVertices; i++) {
                if (tabla[i].getExtra() != 1) {
                    verticeOrig = i;
                    repetir = true;
                    break;
                }
            }
        }
        return L;
    }

    private LinkedList recorrido_profundidadR(String VerticeOrigen, LinkedList L) {
        int VerticeOrig = insVertice(VerticeOrigen);
        tabla[VerticeOrig].setExtra(1);
        L.add(tabla[VerticeOrig].getNombre());

        for (int i = 0; i < numVertices; i++) {
            if (matriz[VerticeOrig][i] != 0 && tabla[i].getExtra() != 1) {
                recorrido_profundidadR(tabla[i].getNombre(), L);
            }
        }
        return L;
    }

    public LinkedList recorrido_profundidad(String VerticeOrig) {
        limpiarDatos();
        LinkedList<String> L = new LinkedList<>();
        recorrido_profundidadR(VerticeOrig, L);
        for (int j = 0; j < numVertices; j++) {//este for se hace para los vertices aislados del grafo no se queden fuera del recorrido como es el caso del vertice E en el grafo del main
            if (tabla[j].getExtra() != 1) {
                recorrido_profundidadR(tabla[j].getNombre(), L);
            }
        }
        return L;
    }

    private void limpiarDatos() {
        for (int i = 0; i < numVertices; i++) {
            tabla[i].setExtra(0);
        }
    }

    private void inicializarCaminos() {
        for (int i = 0; i < numVertices; i++) {
            tabla[i].setDist(INFINITO);
            tabla[i].setAnt(-1);
            tabla[i].setExtra(0);
        }
    }

    public LinkedList<String> getCamino(String verticeDestino) {
        LinkedList<String> camino = new LinkedList<>();
        int destino = buscar(verticeDestino);
        if (verticeOrigenActual == null || destino == -1) {
            return camino;
        }
        int origen = buscar(verticeOrigenActual);
        if (origen == -1) {
            return camino;
        }
        int actual = destino;
        while (actual != -1) {
            camino.addFirst(tabla[actual].getNombre());
            if (actual == origen) {
                break;
            }
            actual = tabla[actual].getAnt();
        }
        if (camino.isEmpty() || !camino.getFirst().equals(verticeOrigenActual)) {
            return new LinkedList<>();
        }
        return camino;
    }

    public void imprimirCamino(String verticeDestino) {
        if (verticeOrigenActual == null) {
            System.out.println("No existe un origen para reconstruir el camino");
            return;
        }
        LinkedList<String> camino = getCamino(verticeDestino);
        if (camino.isEmpty()) {
            System.out.println("No existe camino desde " + verticeOrigenActual + " hasta " + verticeDestino);
            return;
        }
        System.out.print("Camino desde " + verticeOrigenActual + " hasta " + verticeDestino + ": ");
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public void imprimirGrafo() {
        for (int h = 0; h < numVertices; h++) {
            System.out.print("\t" + tabla[h].getNombre());
        }
        System.out.println("");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(tabla[i].getNombre());
            for (int j = 0; j < numVertices; j++) {
                System.out.print("\t" + matriz[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void caminoMSinPeso(String VerticeOrigen) {
        int verticeOrig = buscar(VerticeOrigen);
        if (verticeOrig == -1) {
            System.out.println("El vértice " + VerticeOrigen + " no existe");
            return;
        }
        verticeOrigenActual = VerticeOrigen;
        Queue<Integer> q = new LinkedList<>();
        inicializarCaminos();
        tabla[verticeOrig].setDist(0);
        q.add(verticeOrig);
        while (!q.isEmpty()) {
            int actual = q.remove();
            for (int i = 0; i < numVertices; i++) {
                if (matriz[actual][i] != 0 && tabla[i].getDist() == INFINITO) {
                    tabla[i].setDist(tabla[actual].getDist() + 1);
                    tabla[i].setAnt(actual);
                    q.add(i);
                }
            }
        }
    }

    public boolean caminoMConPesoPositivo(String verticeOrig) {
        int verticeOrigen = buscar(verticeOrig);
        if (verticeOrigen == -1) {
            return false;
        }
        verticeOrigenActual = verticeOrig;
        inicializarCaminos();
        PriorityQueue<Vertice> cp = new PriorityQueue<>();
        tabla[verticeOrigen].setDist(0);
        cp.add(new Vertice(verticeOrig, 0));
        while (!cp.isEmpty()) {
            Vertice ver = cp.remove();
            int v = buscar(ver.getNombre());
            if (tabla[v].getExtra() == 1) {
                continue;
            }
            tabla[v].setExtra(1);
            for (int i = 0; i < numVertices; i++) {
                float costo = matriz[v][i];
                if (costo != 0) {
                    if (costo < 0) {
                        return false;
                    }
                    if (tabla[i].getDist() > tabla[v].getDist() + costo) {
                        tabla[i].setDist(tabla[v].getDist() + costo);
                        tabla[i].setAnt(v);
                        cp.add(new Vertice(tabla[i].getNombre(), tabla[i].getDist()));
                    }
                }
            }
        }
        return true;
    }

    public boolean caminoMConPesosNegativos(String VerticeOrigen) {
        int verticeOrig = buscar(VerticeOrigen);
        if (verticeOrig == -1) {
            return false;
        }
        verticeOrigenActual = VerticeOrigen;
        inicializarCaminos();
        tabla[verticeOrig].setDist(0);

        for (int i = 0; i < numVertices - 1; i++) {
            boolean cambio = false;
            for (int v = 0; v < numVertices; v++) {
                if (tabla[v].getDist() == INFINITO) {
                    continue;
                }
                for (int w = 0; w < numVertices; w++) {
                    float costo = matriz[v][w];
                    if (costo != 0 && tabla[w].getDist() > tabla[v].getDist() + costo) {
                        tabla[w].setDist(tabla[v].getDist() + costo);
                        tabla[w].setAnt(v);
                        cambio = true;
                    }
                }
            }
            if (!cambio) {
                break;
            }
        }

        for (int v = 0; v < numVertices; v++) {
            if (tabla[v].getDist() == INFINITO) {
                continue;
            }
            for (int w = 0; w < numVertices; w++) {
                float costo = matriz[v][w];
                if (costo != 0 && tabla[w].getDist() > tabla[v].getDist() + costo) {
                    return false;
                }
            }
        }
        return true;
    }

    public LinkedList<String> ordenamientoTopologico() {
        int[] gradosEntrada = new int[numVertices];
        Queue<Integer> q = new LinkedList<>();
        LinkedList<String> orden = new LinkedList<>();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matriz[i][j] != 0) {
                    gradosEntrada[j]++;
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            if (gradosEntrada[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.remove();
            orden.add(tabla[u].getNombre());
            for (int v = 0; v < numVertices; v++) {
                if (matriz[u][v] != 0) {
                    gradosEntrada[v]--;
                    if (gradosEntrada[v] == 0) {
                        q.add(v);
                    }
                }
            }
        }
        return orden;
    }

    public boolean caminoMAciclico(String verticeO) {
        int verticeOrigen = buscar(verticeO);
        if (verticeOrigen == -1) {
            return false;
        }
        verticeOrigenActual = verticeO;
        inicializarCaminos();
        LinkedList<String> orden = ordenamientoTopologico();
        if (orden.size() != numVertices) {
            return false;
        }

        tabla[verticeOrigen].setDist(0);
        for (String nombre : orden) {
            int u = buscar(nombre);
            if (tabla[u].getDist() == INFINITO) {
                continue;
            }
            for (int v = 0; v < numVertices; v++) {
                float costo = matriz[u][v];
                if (costo != 0 && tabla[v].getDist() > tabla[u].getDist() + costo) {
                    tabla[v].setDist(tabla[u].getDist() + costo);
                    tabla[v].setAnt(u);
                }
            }
        }
        return true;
    }

}
