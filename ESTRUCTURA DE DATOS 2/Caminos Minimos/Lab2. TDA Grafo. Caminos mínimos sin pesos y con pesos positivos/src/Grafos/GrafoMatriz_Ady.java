/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class GrafoMatriz_Ady implements Grafo {

    private static int TAMANHO_TABLA_INI = 10;
    private int numVertices;
    private float[][] matriz;
    private Vertice[] tabla;
    private float INFINITO = Float.MAX_VALUE;

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
            tabla[i].setAnt(-1);
            tabla[i].setDist(INFINITO);
        }
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
        } else {
            Queue<Integer> q = new LinkedList<>();
            limpiarDatos();
            tabla[verticeOrig].setDist(0);
            tabla[verticeOrig].setExtra(1);
            q.add(verticeOrig);
            while (!q.isEmpty()) {
                int actual = q.remove();
                for (int i = 0; i < numVertices; i++) {
                    if (matriz[actual][i] != 0 && tabla[i].getExtra() != 1) {
                        tabla[i].setExtra(1);
                        tabla[i].setDist(tabla[actual].getDist() + 1);
                        tabla[i].setAnt(actual);
                        q.add(i);
                    }
                }
            }
        }
    }

    public boolean caminoMConPesoPositivo(String verticeOrig) {
        int v, w;
        float cvw;
        limpiarDatos();
        int verticeOrigen = buscar(verticeOrig);
        if (verticeOrigen == -1) {
            return false;
        }

        Vertice verO = new Vertice(tabla[verticeOrigen].getNombre(), 0);
        PriorityQueue<Vertice> cp = new PriorityQueue<>();
        tabla[verticeOrigen].setDist(0);
        cp.add(verO);

        while (!cp.isEmpty()) {
            Vertice ver = cp.remove();
            v = buscar(ver.getNombre());
            if (tabla[v].getExtra() == 0) {
                tabla[v].setExtra(1);
                for (int i = 0; i < numVertices; i++) {
                    cvw = matriz[v][i];
                    if (cvw < 0) {
                        return false;
                    }
                    if (cvw != 0 && tabla[i].getDist() > tabla[v].getDist() + cvw) {
                        tabla[i].setDist(tabla[v].getDist() + cvw);
                        tabla[i].setAnt(v);
                        Vertice vecino = new Vertice(tabla[i].getNombre(), tabla[i].getDist());
                        cp.add(vecino);
                    }
                }
            }
        }
        return true;
    }

    public void imprimirCamino(String verticeDestino) {
        int verticeDest = buscar(verticeDestino);
        if (verticeDest == -1) {
            System.out.println("El vértice " + verticeDestino + " no existe");
        } else if (tabla[verticeDest].getDist() == INFINITO) {
            System.out.println(verticeDestino + " es inalcanzable");
        } else {
            imprimirCaminoRec(verticeDest);
            System.out.println(" (el costo es " + tabla[verticeDest].getDist() + ")");
        }
    }

    private void imprimirCaminoRec(int verticeDest) {
        if (tabla[verticeDest].getAnt() != -1 && tabla[verticeDest].getDist() != 0) {
            imprimirCaminoRec(tabla[verticeDest].getAnt());
            System.out.print(" -> ");
        }
        System.out.print(tabla[verticeDest].getNombre());
    }
    
}
