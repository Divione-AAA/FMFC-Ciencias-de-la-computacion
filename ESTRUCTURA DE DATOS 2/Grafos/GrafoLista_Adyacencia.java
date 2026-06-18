/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;


public class GrafoLista_Adyacencia implements Grafo {

    private static int TAMANHO_TABLA_INI = 10;
    private int numVertices;
    private VerticeExt[] tabla;

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

    public LinkedList<String> recorrido_amplitud(String VerticeOrigen) {
            limpiarDatos();
            LinkedList<String> resultado = new LinkedList<String>();
            int inicio = buscar(VerticeOrigen);
            if (inicio == -1) {
                return resultado;
            }
            Queue<Integer> q = new LinkedList<Integer>();
            tabla[inicio].setExtra(1);
            q.add(inicio);
            while (!q.isEmpty()) {
                int u = q.remove();
                resultado.addLast(tabla[u].getNombre());
                LinkedList<Arista> L = tabla[u].getLista();
                for (int k = 0; k < L.size(); k++) {
                    Arista a = L.get(k);
                    int v = a.getDestino();
                    if (tabla[v].getExtra() == 0) {
                        tabla[v].setExtra(1);
                        q.add(v);
                    }
                }
            }
            return resultado;
    }

    private LinkedList recorrido_profundidadR(String VerticeOrigen, LinkedList L) {
            int i = buscar(VerticeOrigen);
            if (i == -1) {
                return L;
            }
            tabla[i].setExtra(1);
            L.addLast(tabla[i].getNombre());
            LinkedList<Arista> lista = tabla[i].getLista();
            for (int k = 0; k < lista.size(); k++) {
                Arista a = lista.get(k);
                int v = a.getDestino();
                if (tabla[v].getExtra() == 0) {
                    recorrido_profundidadR(tabla[v].getNombre(), L);
                }
            }
            return L;
    }
 
    public LinkedList recorrido_profundidad(String VerticeOrig) {
            limpiarDatos();
            LinkedList<String> L = new LinkedList<String>();
            int inicio = buscar(VerticeOrig);
            if (inicio == -1) {
                return L;
            }
            return recorrido_profundidadR(VerticeOrig, L);
        
    }

    public void imprimirGrafo() {
            for (int i = 0; i < numVertices; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(tabla[i].getNombre()).append(": ");
                LinkedList<Arista> L = tabla[i].getLista();
                for (int k = 0; k < L.size(); k++) {
                    Arista a = L.get(k);
                    int dest = a.getDestino();
                    sb.append("(").append(tabla[dest].getNombre()).append(", ").append(a.getCosto()).append(")");
                    if (k < L.size() - 1) sb.append(" -> ");
                }
                System.out.println(sb.toString());
            }
    }
}
