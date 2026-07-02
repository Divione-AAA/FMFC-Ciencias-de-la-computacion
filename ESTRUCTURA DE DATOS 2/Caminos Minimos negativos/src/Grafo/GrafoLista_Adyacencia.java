/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class GrafoLista_Adyacencia implements Grafo {

    private static int TAMANHO_TABLA_INI = 10;
    private int numVertices;
    private VerticeExt[] tabla;
    private float INFINITO = Float.MAX_VALUE;
    private String verticeOrigenActual;

    public GrafoLista_Adyacencia() {
        numVertices = 0;
        tabla = new VerticeExt[TAMANHO_TABLA_INI];

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
        VerticeExt v1 = new VerticeExt(nombre);
        if (tabla.length == numVertices) {
            duplicarVectorTabla();
        }
        tabla[numVertices] = v1;
        return numVertices++;
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

    private void duplicarVectorTabla() {
        VerticeExt[] nuevaTabla = new VerticeExt[tabla.length * 2];
        System.arraycopy(tabla, 0, nuevaTabla, 0, tabla.length);
        tabla = nuevaTabla;
    }

    public void insArista(String origen, String destino, float costo) {
        int origen2 = insVertice(origen);
        int destino2 = insVertice(destino);
        Arista a1 = new Arista(destino2, costo);
        tabla[origen2].getLista().addLast(a1);

    }

    public void insArista(String origen, String destino) {
        int origen2 = insVertice(origen);
        int destino2 = insVertice(destino);
        Arista a1 = new Arista(destino2, 0);
        tabla[origen2].getLista().addLast(a1);

    }

    public void elimArista(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        LinkedList<Arista> L;
        if (i != -1 && j != -1) {
            L = tabla[i].getLista();
            for (int k = 0; k < L.size(); k++) {
                Arista x = L.get(k);
                int dest = x.getDestino();
                if (dest == j) {
                    L.remove(k);
                    break;
                }
            }
        } else {
            System.out.println("la arista no existe");
        }
    }

    public void elimArista2(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        Iterator it;
        if (i != -1 && j != -1) {
            it = tabla[i].getLista().iterator();
            while (it.hasNext()) {
                Arista w = (Arista) it.next();
                int dest = w.getDestino();
                if (j == dest) {
                    it.remove();
                    break;
                }
            }
        } else {
            System.out.println("la arista no existe");
        }
    }

    public void elimVertice(String nombre) {
        int i = buscar(nombre);
        if (i != -1) {
            tabla[i] = tabla[--numVertices];
            LinkedList<Arista> L;
            for (int j = 0; j < numVertices; j++) {
                L = tabla[j].getLista();
                for (int k = 0; k < L.size(); k++) {
                    Arista x = L.get(k);
                    int destino = x.getDestino();
                    if (i == destino) {
                        L.remove(k);
                    }
                    if (numVertices == destino) {
                        L.get(k).setDestino(i);
                    }
                }

            }
        } else {
            System.out.println("El v�rtice que se quiere eliminar no existe");
        }
    }

    public boolean esAdyacente(String vertice1, String vertice2) {//aqu� se usa el iterador de la lista para que vean un ejemplo us�ndolo
        int i = buscar(vertice1);
        int j = buscar(vertice2);
        Iterator it;
        if (i != -1 && j != -1) {
            it = tabla[i].getLista().iterator();
            while (it.hasNext()) {
                Arista w = (Arista) it.next();
                int dest = w.getDestino();
                if (j == dest) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean esAdyacente2(String vertice1, String vertice2) {//es el mismo m�todo anterior pero sin usar el iterador
        int i = buscar(vertice1);
        int j = buscar(vertice2);
        LinkedList<Arista> L;
        if (i != -1 && j != -1) {
            L = tabla[i].getLista();
            for (int k = 0; k < L.size(); k++) {
                Arista x = L.get(k);
                int destino = x.getDestino();
                if (destino == j) {
                    return true;
                }
            }
        }
        return false;
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

    public LinkedList<String> recorrido_amplitud(String VerticeOrigen) {
        limpiarDatos();
        boolean repetir = true;
        int VerticeOrig = insVertice(VerticeOrigen);
        Queue<Integer> q = new LinkedList<>();
        LinkedList<String> L = new LinkedList<>();
        Iterator it;
        while (repetir) {//este ciclo es necesario para poder incorporar al recorrido los vertices aislados
            tabla[VerticeOrig].setExtra(1);
            L.add(tabla[VerticeOrig].getNombre());
            q.add(VerticeOrig);
            while (!q.isEmpty()) {
                int actual = q.remove();
                it = tabla[actual].getLista().iterator();
                while (it.hasNext()) {
                    Arista w = (Arista) it.next();
                    int dest = w.getDestino();
                    if (tabla[dest].getExtra() != 1) {
                        tabla[dest].setExtra(1);
                        L.add(tabla[dest].getNombre());
                        q.add(dest);
                    }

                }
            }
            repetir = false;
            for (int j = 0; j < numVertices; j++) {//este for se hace para los vértices aislados del grafo no se queden fuera del recorrido como es el caso del vertice E en el grafo del main
                if (tabla[j].getExtra() != 1) {
                    VerticeOrig = j;
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
        //System.out.print(tabla[VerticeOrig].getNombre());
        Iterator it;
        it = tabla[VerticeOrig].getLista().iterator();
        while (it.hasNext()) {
            Arista w = (Arista) it.next();
            int dest = w.getDestino();
            if (tabla[dest].getExtra() != 1) {
                recorrido_profundidadR(tabla[dest].getNombre(), L);
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

    public void imprimirVerticesEnOrden() {
        for (int i = 0; i < numVertices; i++) {
            if (i == numVertices - 1) {
                System.out.print(tabla[i].getNombre() + "\n");
            } else {
                System.out.print(tabla[i].getNombre() + ", ");
            }
        }
    }

    public void imprimirGrafo() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + "  [" + tabla[i].getNombre() + "] ---> ");
            Iterator it = tabla[i].getLista().iterator();
            if (!it.hasNext()) {
                System.out.print("||");
            } else {
                while (it.hasNext()) {
                    Arista w = (Arista) it.next();
                    int destino = w.getDestino();
                    float costo = w.getCosto();
                    System.out.print("[" + destino + " | " + costo + "]" + " ---> ");
                }
                System.out.print("||");
            }
            System.out.println("");
        }
    }
    
    public void caminoMSinPeso(String VerticeOrigen) {
        int verticeOrig = buscar(VerticeOrigen);
        if (verticeOrig == -1) {
            System.out.println("El vértice " + VerticeOrigen + " no existe");
        } else {
            verticeOrigenActual = VerticeOrigen;
            Queue<Integer> q = new LinkedList<>();
            inicializarCaminos();
            tabla[verticeOrig].setDist(0);
            q.add(verticeOrig);
            Iterator it;
            while (!q.isEmpty()) {
                int actual = q.remove();
                it = tabla[actual].getLista().iterator();
                while (it.hasNext()) {
                    Arista a = (Arista) it.next();
                    int w = a.getDestino();
                    if (tabla[w].getDist() == INFINITO) {
                        tabla[w].setDist(tabla[actual].getDist() + 1);
                        tabla[w].setAnt(actual);
                        q.add(w);
                    }
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
        Iterator it;
        while (!cp.isEmpty()) {
            Vertice ver = cp.remove();
            int v = buscar(ver.getNombre());
            if (tabla[v].getExtra() == 1) {
                continue;
            }
            tabla[v].setExtra(1);
            it = tabla[v].getLista().iterator();
            while (it.hasNext()) {
                Arista a = (Arista) it.next();
                int w = a.getDestino();
                float cvw = a.getCosto();
                if (cvw < 0) {
                    return false;
                }
                if (tabla[w].getDist() > tabla[v].getDist() + cvw) {
                    tabla[w].setDist(tabla[v].getDist() + cvw);
                    tabla[w].setAnt(v);
                    cp.add(new Vertice(tabla[w].getNombre(), tabla[w].getDist()));
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
                Iterator it = tabla[v].getLista().iterator();
                while (it.hasNext()) {
                    Arista a = (Arista) it.next();
                    int w = a.getDestino();
                    float cvw = a.getCosto();
                    if (tabla[w].getDist() > tabla[v].getDist() + cvw) {
                        tabla[w].setDist(tabla[v].getDist() + cvw);
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
            Iterator it = tabla[v].getLista().iterator();
            while (it.hasNext()) {
                Arista a = (Arista) it.next();
                int w = a.getDestino();
                if (tabla[w].getDist() > tabla[v].getDist() + a.getCosto()) {
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
            Iterator it = tabla[i].getLista().iterator();
            while (it.hasNext()) {
                Arista a = (Arista) it.next();
                gradosEntrada[a.getDestino()]++;
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
            Iterator it = tabla[u].getLista().iterator();
            while (it.hasNext()) {
                Arista a = (Arista) it.next();
                int v = a.getDestino();
                gradosEntrada[v]--;
                if (gradosEntrada[v] == 0) {
                    q.add(v);
                }
            }
        }
        return orden;
    }

    public boolean caminoMAciclico(String verticeO) {
        int verticeOrig = buscar(verticeO);
        if (verticeOrig == -1) {
            return false;
        }
        verticeOrigenActual = verticeO;
        inicializarCaminos();
        LinkedList<String> orden = ordenamientoTopologico();
        if (orden.size() != numVertices) {
            return false;
        }

        tabla[verticeOrig].setDist(0);
        for (String nombre : orden) {
            int v = buscar(nombre);
            if (tabla[v].getDist() == INFINITO) {
                continue;
            }
            Iterator it = tabla[v].getLista().iterator();
            while (it.hasNext()) {
                Arista w = (Arista) it.next();
                int dest = w.getDestino();
                if (tabla[dest].getDist() > tabla[v].getDist() + w.getCosto()) {
                    tabla[dest].setDist(tabla[v].getDist() + w.getCosto());
                    tabla[dest].setAnt(v);
                }
            }
        }
        return true;
    }

}
